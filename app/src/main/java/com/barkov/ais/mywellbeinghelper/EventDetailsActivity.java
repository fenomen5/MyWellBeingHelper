package com.barkov.ais.mywellbeinghelper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barkov.ais.mywellbeinghelper.entity.SportEvent;
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

public class EventDetailsActivity extends AppCompatActivity
    implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    DataBaseHelper dbHelper;
    SportEvent event;
    WellnessClubGallery eventGallery;
    TextView lblEventAddress, lblStartDate, lblEventDescription;
    ScrollGoogleMap eventMap;
    ImageSwitcher imgSw;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new Handler();
        FloatingActionButton fab = findViewById(R.id.fabEvent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int eventId = getIntent().getIntExtra("event_id", 0);
        event = getEventData(eventId);
        try {
            eventGallery = getEventGallery(event.getGalleryId());
        } catch (NullPointerException e) {
            eventGallery = null;
        }
        CollapsingToolbarLayout clubToolbar = findViewById(R.id.toolbarLayoutSelectedEvent);
        clubToolbar.setTitle(event.getTitle());

        lblEventAddress = findViewById(R.id.txtAddressSelectedEvent);
        lblEventAddress.setText(event.getAddress());

        lblStartDate = findViewById(R.id.lblStartDateSelectedEvent);
        lblStartDate.setText("Starts at: " + event.getStartDate());

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        eventMap = findViewById(R.id.mapViewSelectedEvent);
        eventMap.onCreate(mapViewBundle);
        eventMap.getMapAsync(this);

        imgSw = findViewById(R.id.imgSwSelectedEvent);
        initImageSwitcher();

        lblEventDescription = findViewById(R.id.lblDescriptionSelectedEvent);
        lblEventDescription.setText(event.getDescription());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        eventMap.onSaveInstanceState(mapViewBundle);
    }

    protected SportEvent getEventData(int id)
    {
        dbHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);

        RuntimeExceptionDao<SportEvent, Integer> eventsDao = dbHelper.getSportEventRuntimeDao();

        return eventsDao.queryForId(id);
    }

    protected WellnessClubGallery getEventGallery(int id)
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
        LatLng ny = new LatLng(event.getLongitude(), event.getLatitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        gMap.addMarker(markerOptions);

        gMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventMap.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventMap.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventMap.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        eventMap.onDestroy();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        eventMap.onStop();
    }

    protected void initImageSwitcher()
    {
        if (eventGallery == null) {

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,0);
            TextView lblEventGallery = findViewById(R.id.lblEventGallery);
            lblEventGallery.setLayoutParams(lp);
            imgSw.setLayoutParams(lp);
            return;
        }
        Thread thread = new Thread(new Runnable() {

            GalleryLoader loader = new GalleryLoader(eventGallery.getPath(), EventDetailsActivity.this);
            ArrayList<Bitmap> images = loader.getImages();
            Gallery gallery = new Gallery(EventDetailsActivity.this, imgSw, images, 0);
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
