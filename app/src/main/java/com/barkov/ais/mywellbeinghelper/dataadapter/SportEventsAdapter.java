package com.barkov.ais.mywellbeinghelper.dataadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.barkov.ais.mywellbeinghelper.ClubDetailsActivity;
import com.barkov.ais.mywellbeinghelper.DataBaseHelper;
import com.barkov.ais.mywellbeinghelper.EventDetailsActivity;
import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.entity.SportEvent;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClub;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class SportEventsAdapter extends BaseAdapter {

    DataBaseHelper dbHelper;
    Context mContext;
    List<SportEvent> events;
    LayoutInflater inflater;

    public SportEventsAdapter(Context context) {
        mContext = context;

        dbHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);

        RuntimeExceptionDao<SportEvent, Integer> eventsDao = dbHelper.getSportEventRuntimeDao();

        events = eventsDao.queryForAll();
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
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
            convertView = inflater.inflate(R.layout.sport_events_list_item, parent, false);
        }

        final SportEvent event = events.get(position);
        TextView lblEventTitle = convertView.findViewById(R.id.lblEventTitle);
        lblEventTitle.setText(event.getTitle());

        TextView lblEventAddress = convertView.findViewById(R.id.lblEventAddress);
        lblEventAddress.setText(event.getAddress());

        TextView lblStartDate = convertView.findViewById(R.id.lblStartDate);
        lblStartDate.setText(event.getStartDate());

        final int pos = position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EventDetailsActivity.class);
                intent.putExtra("event_id", event.getId());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


}
