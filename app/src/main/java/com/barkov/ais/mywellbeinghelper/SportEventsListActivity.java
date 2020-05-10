package com.barkov.ais.mywellbeinghelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.barkov.ais.mywellbeinghelper.dataadapter.SportEventsAdapter;
import com.barkov.ais.mywellbeinghelper.dataadapter.WellnessClubsAdapter;

public class SportEventsListActivity extends AppCompatActivity {

    ListView listSportEvents;
    SportEventsAdapter sportEventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_events_list);

        listSportEvents = findViewById(R.id.listSportEvents);

        sportEventsAdapter = new SportEventsAdapter(this);

        listSportEvents.setAdapter(sportEventsAdapter);
    }

    public void displayEventSearchDialog(View v)
    {

    }
}
