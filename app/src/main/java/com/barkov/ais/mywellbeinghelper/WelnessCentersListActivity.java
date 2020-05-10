package com.barkov.ais.mywellbeinghelper;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.barkov.ais.mywellbeinghelper.dataadapter.WellnessClubsAdapter;

import java.util.HashMap;

public class WelnessCentersListActivity extends AppCompatActivity {

    Button btnSetFilters;
    ListView listWelnessCenters;
    WellnessClubsAdapter wellnessClubsAdapter;
    TextView lblSearchParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welness_centers_list);

        listWelnessCenters = findViewById(R.id.listWelnessCenters);
        wellnessClubsAdapter = new WellnessClubsAdapter(this);

        listWelnessCenters.setAdapter(wellnessClubsAdapter);
        lblSearchParams = findViewById(R.id.lblSearchParams);
    }

    public void displaySearchDialog(View v)
    {
        final Dialog dlg = new Dialog(this);
        dlg.setTitle("Query parameters");
        dlg.setContentView(R.layout.dialog_search_club);

        EditText txtQueryName, txtQueryAddress;
        SeekBar seekBarStartHour, seekBarEndHour;

        txtQueryName = dlg.findViewById(R.id.txtSearchParamClubName);
        txtQueryAddress = dlg.findViewById(R.id.txtSearchParamClubAddress);
        seekBarStartHour = dlg.findViewById(R.id.seekStartHour);
        seekBarEndHour = dlg.findViewById(R.id.seekEndHour);

        final HashMap<String,Object> searchParams = new HashMap<String, Object>();

        btnSetFilters = dlg.findViewById(R.id.btnSetClubFilters);

        final EditText queryName = txtQueryName;
        final EditText queryAddress = txtQueryAddress;
        final SeekBar startHour = seekBarStartHour;
        final SeekBar endHour = seekBarEndHour;
        final TextView searchPanel = lblSearchParams;
        btnSetFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchParams.put("club_name",queryName.getText());
                searchParams.put("club_address",queryAddress.getText());
                searchParams.put("club_start_hour", startHour.getProgress());
                searchParams.put("club_end_hour",endHour.getProgress());

                String params ="";
                for (Object str:searchParams.values()) {
                    if (TextUtils.isEmpty(str.toString())) {
                        continue;
                    }
                    params += str + ";";
                }

                searchPanel.setText(params);

                wellnessClubsAdapter.setFilterParams(searchParams);
                listWelnessCenters.invalidateViews();
                listWelnessCenters.refreshDrawableState();
                dlg.hide();
            }
        });

        addSeekBarValueIndicator(dlg, (SeekBar) dlg.findViewById(R.id.seekStartHour), (TextView) dlg.findViewById(R.id.seekStartHourValue));
        addSeekBarValueIndicator(dlg, (SeekBar) dlg.findViewById(R.id.seekEndHour), (TextView) dlg.findViewById(R.id.seekEndHourValue));
        dlg.show();
    }

    protected void addSeekBarValueIndicator(Dialog dlg, SeekBar bar, TextView txt)
    {

        txt.setText(""+bar.getProgress());
        final TextView t = txt;
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                t.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
