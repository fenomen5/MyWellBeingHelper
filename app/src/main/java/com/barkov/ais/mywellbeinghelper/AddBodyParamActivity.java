package com.barkov.ais.mywellbeinghelper;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.barkov.ais.mywellbeinghelper.dataadapter.BodyParamAdapter;
import com.barkov.ais.mywellbeinghelper.dataadapter.CityAdapter;
import com.barkov.ais.mywellbeinghelper.entity.BodyParam;
import com.barkov.ais.mywellbeinghelper.entity.BodyParamType;
import com.barkov.ais.mywellbeinghelper.entity.City;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBodyParamActivity extends AppCompatActivity
        implements View.OnClickListener {

    Button btnAddParam;
    Spinner spinner;
    TextView txtParamDate;
    EditText txtParamValue;
    BodyParamAdapter bodyParamsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_body_param);

        txtParamDate = findViewById(R.id.txtParamDate);
        txtParamDate.setOnClickListener(this);
        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        txtParamDate.setText(date);

        bodyParamsAdapter = new BodyParamAdapter(this);
        spinner = findViewById(R.id.spnParamType);
        spinner.setAdapter(bodyParamsAdapter);

        btnAddParam = findViewById(R.id.btnSaveParam);
        btnAddParam.setOnClickListener(this);

        txtParamValue = findViewById(R.id.txtParamValue);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtParamDate:
                displayDatePcikerDialog();
                break;
            case R.id.btnSaveParam:
                addParamValue();
                break;
        }
    }

    protected void displayDatePcikerDialog()
    {
        final Dialog dlg = new Dialog(this);

        dlg.setContentView(R.layout.dialog_select_date);

        CalendarView cldSelectDate = dlg.findViewById(R.id.cldSelectDate);
        cldSelectDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                txtParamDate.setText(""+
                        String.format("%2s", ""+dayOfMonth).replace(' ', '0') + "/" +
                        String.format("%2s", ""+month).replace(' ', '0') + "/" +
                        year);
                dlg.dismiss();
            }
        });

        dlg.show();
    }

    protected void addParamValue()
    {
        if (TextUtils.isEmpty(txtParamValue.getText().toString())) {
            return;
        }

        DataBaseHelper dbHelper = OpenHelperManager.getHelper( this, DataBaseHelper.class);

        RuntimeExceptionDao<BodyParam, Integer> bodyParamTypeDao = dbHelper.getBodyParamRuntimeDao();
        Map<String,Object> values = new HashMap<String, Object>();
        values.put("created_at", txtParamDate.getText().toString());
        values.put("param_type", ((BodyParamType)spinner.getSelectedItem()).getId());

        List<BodyParam> bodyParams = bodyParamTypeDao.queryForFieldValues(values);
        if (bodyParams.size() == 0) {
            bodyParamTypeDao.create(new BodyParam(
                    ((BodyParamType)spinner.getSelectedItem()).getId(),
                    Float.valueOf(txtParamValue.getText().toString()),
                    txtParamDate.getText().toString()
            ));
        } else {
            bodyParams.get(0).setValue(Float.valueOf(txtParamValue.getText().toString()));
            bodyParamTypeDao.update(bodyParams.get(0));
        }
        txtParamValue.setText("");
        Toast.makeText(this, "parameter is saved", Toast.LENGTH_SHORT).show();
    }
}
