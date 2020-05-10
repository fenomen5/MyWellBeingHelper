package com.barkov.ais.mywellbeinghelper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ImageSwitcher;

import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClub;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClubGallery;
import com.barkov.ais.mywellbeinghelper.filetools.GalleryLoader;
import com.barkov.ais.mywellbeinghelper.google.map.ScrollGoogleMap;
import com.barkov.ais.mywellbeinghelper.images.Gallery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

public class ClubDetailsActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    DataBaseHelper dbHelper;
    WellnessClub club;
    WellnessClubGallery clubGallery;
    TextView lblClubAddress, lblWorkingHours, lblClubDescription;
    ScrollGoogleMap clubMap;
    ImageSwitcher imgSw;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new Handler();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int clubId = getIntent().getIntExtra("club_id", 0);
        club = getClubData(clubId);

        try {
            clubGallery = getClubGallery(club.getGalleryId());
        } catch (Exception e) {
            clubGallery = null;
        }

        RatingBar ratingBarClub = findViewById(R.id.ratingBarSelectedClub);

        ratingBarClub.setNumStars(5);
        ratingBarClub.setStepSize(0.1f);
        ratingBarClub.setRating(club.getRating());

        CollapsingToolbarLayout clubToolbar = findViewById(R.id.toolbarLayoutSelectedClub);
        clubToolbar.setTitle(club.getTitle());

        lblClubAddress = findViewById(R.id.txtAddressSelectedClub);
        lblClubAddress.setText(club.getAddress());

        lblWorkingHours = findViewById(R.id.lblWorkingHoursSelectedClub);
        lblWorkingHours.setText("Working hours: " + club.getWorkingHours());

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        clubMap = findViewById(R.id.mapViewSelectedClub);
        clubMap.onCreate(mapViewBundle);
        clubMap.getMapAsync(this);

        imgSw = findViewById(R.id.imgSwSelectedClub);
        initImageSwitcher();

        lblClubDescription = findViewById(R.id.lblDescriptionSelectedClub);
        lblClubDescription.setText(club.getDescription());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        clubMap.onSaveInstanceState(mapViewBundle);
    }

    protected WellnessClub getClubData(int id)
    {
        dbHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);

        RuntimeExceptionDao<WellnessClub, Integer> clubsDao = dbHelper.getClubRuntimeDao();

        return clubsDao.queryForId(id);
    }

    protected WellnessClubGallery getClubGallery(int id)
    {
        dbHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);

        RuntimeExceptionDao<WellnessClubGallery, Integer> clubsDao = dbHelper.getGalleryRuntimeDao();

        return clubsDao.queryForId(id);
    }

       @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMinZoomPreference(12);
        googleMap.setIndoorEnabled(true);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        setMarker(googleMap);
    }

    protected void setMarker(GoogleMap gMap)
    {
        LatLng ny = new LatLng(club.getLongitude(), club.getLatitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        gMap.addMarker(markerOptions);

        gMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    @Override
    protected void onStart() {
        super.onStart();
        clubMap.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clubMap.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clubMap.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        clubMap.onDestroy();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        clubMap.onStop();
    }

    protected void initImageSwitcher()
    {
        if (clubGallery == null) {

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,0);
            TextView lblClubGallery = findViewById(R.id.lblClubGallery);
            lblClubGallery.setLayoutParams(lp);
            imgSw.setLayoutParams(lp);
            return;
        }

        Thread thread = new Thread(new Runnable() {

            GalleryLoader loader = new GalleryLoader(clubGallery.getPath(), ClubDetailsActivity.this);
            ArrayList<Bitmap> images = loader.getImages();
            Gallery gallery = new Gallery(ClubDetailsActivity.this, imgSw, images, 0);
            boolean startNavigation = true;
            @Override
            public void run() {
                gallery.showSlides();
                while (startNavigation) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            gallery.nextSlide();
                        }
                    });

                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
