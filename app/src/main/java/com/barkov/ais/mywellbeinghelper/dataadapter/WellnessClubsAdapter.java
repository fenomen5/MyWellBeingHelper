package com.barkov.ais.mywellbeinghelper.dataadapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.barkov.ais.mywellbeinghelper.ClubDetailsActivity;
import com.barkov.ais.mywellbeinghelper.DataBaseHelper;

import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClub;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class WellnessClubsAdapter extends BaseAdapter {

    DataBaseHelper dbHelper;
    Context mContext;
    List<WellnessClub> clubs;
    LayoutInflater inflater;

    public WellnessClubsAdapter(Context context) {
        mContext = context;

        dbHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);

        RuntimeExceptionDao<WellnessClub, Integer> clubsDao = dbHelper.getClubRuntimeDao();

        clubs = clubsDao.queryForAll();
    }

    @Override
    public int getCount() {
        return clubs.size();
    }

    @Override
    public Object getItem(int position) {
        return clubs.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.wellness_clubs_list_item, parent, false);
        }

        final WellnessClub club = clubs.get(position);
        TextView lblClubTitle = convertView.findViewById(R.id.lblClubTitle);
        lblClubTitle.setText(club.getTitle());

        RatingBar ratingBarClub = convertView.findViewById(R.id.ratingBarClub);

        ratingBarClub.setNumStars(5);
        ratingBarClub.setStepSize(0.1f);
        ratingBarClub.setRating(club.getRating());

        TextView lblClubAddress = convertView.findViewById(R.id.lblClubAddress);
        lblClubAddress.setText(club.getAddress());

        TextView lblWorkingHours = convertView.findViewById(R.id.lblWorkingHours);
        lblWorkingHours.setText(club.getWorkingHours());

        final int pos = position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ClubDetailsActivity.class);
                intent.putExtra("club_id", club.getId());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setFilterParams(HashMap<String, Object> searchParams)
    {
        dbHelper = OpenHelperManager.getHelper(mContext, DataBaseHelper.class);

        RuntimeExceptionDao<WellnessClub, Integer> clubsDao = dbHelper.getClubRuntimeDao();
        Where where = clubsDao.queryBuilder().where();

        try {
            where.isNotNull("title");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (searchParams.containsKey("club_name") && !TextUtils.isEmpty(searchParams.get("club_name").toString())) {
            try {
                where.and().like("title",
                        "%" + searchParams.get("club_name").toString()+"%");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (searchParams.containsKey("club_address") && !TextUtils.isEmpty(searchParams.get("club_address").toString())) {
            try {
                where.and().like("address",
                        "%" + searchParams.get("club_address").toString()+ "%");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (searchParams.containsKey("club_start_hour")) {
            try {
                where.and().le("open_hour",
                        searchParams.get("club_start_hour"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (searchParams.containsKey("club_end_hour")) {
            try {
                where.and().ge("close_hour",
                        searchParams.get("club_end_hour"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            clubs = clubsDao.query(where.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
