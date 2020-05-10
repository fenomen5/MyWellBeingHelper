package com.barkov.ais.mywellbeinghelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BodyParamsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddBodyParams, btnShowProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_params);

        btnAddBodyParams = findViewById(R.id.btnAddParameter);
        btnAddBodyParams.setOnClickListener(this);

        btnShowProgress = findViewById(R.id.btnProgress);
        btnShowProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddParameter:
                showActivity(AddBodyParamActivity.class);
                break;
            case R.id.btnProgress:
                showActivity(PersonalProgressActivity.class);
                break;
        }
    }

    protected void showActivity(Class activity)
    {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
