package com.barkov.ais.mywellbeinghelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.barkov.ais.mywellbeinghelper.entity.Settings;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.io.IOException;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTimeRun();
            }
        }, 1);
    }

    /**
     * Check for first run of the application
     * @return
     */
    protected boolean isFirstTimeRun()
    {
        dbHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        //dbHelper.onCreate(dbHelper.getWritableDatabase(),dbHelper.getConnectionSource());

        RuntimeExceptionDao<Settings, Integer> settingsDao = dbHelper.getSettingsRuntimeDao();

        List<Settings> settings = settingsDao
                .queryForEq("key", Settings.FIRST_TIME_RUN_KEY);

        if (settings.size() == 0 || settings.get(0).getValue().equals("true")) {
            startSettingsWizardActivity();
        } else {
            startLoginActivity();
        }

        return true;
    }

    /**
     * Run Login activity
     */
   /* {
        startActivity(MainActivity.class);
    }*/
    protected void startLoginActivity()
    {
        startActivity(LoginActivity.class);
    }

    protected void startSettingsWizardActivity()
    {
        startActivity(SettingsWizardActivity.class);
    }

    /**
     * Start selected activity
     * @param activityClassName
     */
    protected void startActivity(Class activityClassName)
    {
        Intent intent = new Intent(this, activityClassName);
        startActivity(intent);
        finish();
    }
}
