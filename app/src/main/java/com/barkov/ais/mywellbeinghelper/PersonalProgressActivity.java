package com.barkov.ais.mywellbeinghelper;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.barkov.ais.mywellbeinghelper.entity.BodyParam;
import com.barkov.ais.mywellbeinghelper.entity.BodyParamType;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PersonalProgressActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series;
    private ArrayList<String> selectedGraphs;
    FloatingActionButton btnGraphSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_progress);
        btnGraphSettings = findViewById(R.id.fabGraphSettings);
        btnGraphSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayGraphDialog(v);
            }
        });
        getGraphColors();
        buildGraph();
    }

    protected List<BodyParam> getBodyParamsData()
    {
        DataBaseHelper dbHelper = OpenHelperManager.getHelper( this, DataBaseHelper.class);
        RuntimeExceptionDao<BodyParam, Integer> bodyParamDao = dbHelper.getBodyParamRuntimeDao();
        return bodyParamDao.queryForAll();
    }

    protected List<BodyParamType> getBodyParamsTypes() {
        DataBaseHelper dbHelper = OpenHelperManager.getHelper( this, DataBaseHelper.class);
        RuntimeExceptionDao<BodyParamType, Integer> bodyParamTypeDao = dbHelper.getBodyParamTypeRuntimeDao();
        return bodyParamTypeDao.queryForAll();
    }
    protected int[] getGraphColors()
    {
        return new int[]{
            getResources().getColor(R.color.graph_blue),
            getResources().getColor(R.color.graph_yellow),
            getResources().getColor(R.color.graph_red),
            getResources().getColor(R.color.graph_purple),
            getResources().getColor(R.color.graph_green)
        };
    }
    public void buildGraph()
    {
        GraphView progressView = findViewById(R.id.progressView);
        progressView.removeAllSeries();

        List <BodyParamType> bodyParamsTypes = getBodyParamsTypes();
        List<BodyParam> bodyParams = getBodyParamsData();
        ArrayList<LineGraphSeries<DataPoint>> seriesSet = new ArrayList<LineGraphSeries<DataPoint>>();

        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");
        Collections.sort(bodyParams, new Comparator<BodyParam>() {

            @Override
            public int compare(BodyParam o1, BodyParam o2) {

                int result = 0;
                try {
                    Date o1Date = format.parse(o1.getCreated_at());
                    Date o2Date = format.parse(o2.getCreated_at());
                    result = o1Date.compareTo(o2Date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return result;
            }
        });

        series = new LineGraphSeries<DataPoint>();
        seriesSet.add(series);
        for (BodyParamType bodyParamType: bodyParamsTypes) {
            series = new LineGraphSeries<DataPoint>();
            series.setTitle(bodyParamType.getName());
            series.setColor(getGraphColors()[bodyParamType.getId()]);
            seriesSet.add(series);
        }

        for (BodyParam bodyParam : bodyParams) {

            Date date = null;
            try {
                date = format.parse(bodyParam.getCreated_at());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DataPoint dp = new DataPoint(date, bodyParam.getValue());
                seriesSet.get(bodyParam.getParam_type())
                    .appendData(dp, true, 500);
        }


        for (LineGraphSeries<DataPoint> series:seriesSet) {
            boolean isSkip = false;
            if (series.isEmpty()) {
                isSkip = true;
            }
            if (selectedGraphs == null) {
                Log.d("dbg", "selectedGraphs is null");
            } else {
                Log.d("dbg", selectedGraphs.toArray().toString());
            }

            if ((selectedGraphs != null)) {
                isSkip = true;
                for (String selectedGraph: selectedGraphs) {
                    if (selectedGraph.equals(series.getTitle())) {
                        isSkip = false;
                    }
                }
            }
            if (isSkip) {
                continue;
            }

            progressView.addSeries(series);
        }

        progressView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        progressView.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        progressView.getGridLabelRenderer().setHumanRounding(false);

        progressView.getViewport().setScalable(true);

        progressView.getLegendRenderer().setVisible(true);
        progressView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

    protected void displayGraphDialog(View v)
    {

        final Dialog dlg = new Dialog(this);
        dlg.setTitle("Select graph");
        dlg.setContentView(R.layout.dialog_graph_settings);

        LinearLayout dialogLayout = dlg.findViewById(R.id.graphSettingsLayout);

        List <BodyParamType> bodyParamsTypes = getBodyParamsTypes();
        Log.d("dbg","bodyParamsTypes.size" + bodyParamsTypes.size());
        for (BodyParamType bodyParamType: bodyParamsTypes) {

            LinearLayout dialogRow = new LinearLayout(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            dialogRow.setLayoutParams(lp);
            CheckBox graphCheckBox = new CheckBox(this);
            graphCheckBox.setText(bodyParamType.getName());
            graphCheckBox.setChecked(true);
            dialogRow.addView(graphCheckBox);
            dialogLayout.addView(dialogRow);
        }


        Button btnSelectGraphs = dlg.findViewById(R.id.btnSelectGraphs);

        final LinearLayout dlgL = dialogLayout;
        btnSelectGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGraphs = new ArrayList<String>();
                int LayoutsCount = dlgL.getChildCount();

                for (int j = 0; j < LayoutsCount ; j++) {
                    ViewGroup layout = (ViewGroup) dlgL.getChildAt(j);
                        View childV = layout.getChildAt(0);
                        if (childV instanceof CheckBox) {
                            Log.d("dbg", "add checkbox" + childV.getId());
                            if (((CheckBox) childV).isChecked()) {
                                selectedGraphs.add(((CheckBox) childV).getText().toString());
                            }
                        }
                }
                buildGraph();
                dlg.dismiss();
            }
        });

        dlg.show();
    }

}
