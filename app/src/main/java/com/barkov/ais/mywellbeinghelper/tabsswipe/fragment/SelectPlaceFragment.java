package com.barkov.ais.mywellbeinghelper.tabsswipe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.SettingsWizardActivity;
import com.barkov.ais.mywellbeinghelper.dataadapter.CityAdapter;
import com.barkov.ais.mywellbeinghelper.entity.City;

import java.util.ArrayList;

public class SelectPlaceFragment extends ValidableFragment {

    Spinner spinner;
    Button btnNext;
    CityAdapter cityAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_select, container, false);

        spinner = view.findViewById(R.id.spinner);

        cityAdapter = new CityAdapter(getContext());

        spinner.setAdapter(cityAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("dbg", "item clicked" + String.valueOf(id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnNext = view.findViewById(R.id.btnLocationNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateValues()) {
                    ((SettingsWizardActivity) getActivity()).nextTab();
                }
            }
        });

        return view;
    }

    protected boolean validateValues()
    {
        Log.d( "dbg", spinner.getSelectedItem().toString());
        return true;
    }

    @Override
    public boolean validateInput()
    {
        return validateValues();
    }

    @Override
    public ArrayMap setProfileFields(ArrayMap list) {

        int cityId = ((City)spinner.getSelectedItem()).getId();

        list.put("city", String.valueOf(cityId));
        return super.setProfileFields(list);
    }
}
