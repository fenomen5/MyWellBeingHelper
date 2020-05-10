package com.barkov.ais.mywellbeinghelper;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnClubs, btnActivities, btnBodyParams, btnAbout;
    FloatingActionButton btnAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClubs = findViewById(R.id.btnClubs);
        btnClubs.setOnClickListener(this);

        btnActivities = findViewById(R.id.btnActivities);
        btnActivities.setOnClickListener(this);

        btnBodyParams = findViewById(R.id.btnBodyParams);
        btnBodyParams.setOnClickListener(this);

        btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(this);

        btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAccount:
                showAccountForm();
                break;
            case R.id.btnClubs:
                showClubsList();
                break;
            case R.id.btnBodyParams:
                showPersonalParams();
                break;
            case R.id.btnActivities:
                showSportEventsList();
                break;
            case R.id.btnAbout:
                showAbout();
                break;



        }
    }

    protected void showClubsList()
    {
        Intent intent = new Intent(this, WelnessCentersListActivity.class);
        startActivity(intent);
    }

    protected void showPersonalParams()
    {
        Intent intent = new Intent(this, BodyParamsActivity.class);
        startActivity(intent);
    }

    protected void showSportEventsList()
    {
        Intent intent = new Intent(this, SportEventsListActivity.class);
        startActivity(intent);
    }

    protected void showAccountForm()
    {
        Intent intent = new Intent(this, SettingsWizardActivity.class);
        intent.putExtra("start_position", 2);
        startActivity(intent);
    }

    protected void showAbout()
    {
        Intent intent = new Intent(this, AboutActivity.class);
        intent.putExtra("start_position", 2);
        startActivity(intent);
    }

}
