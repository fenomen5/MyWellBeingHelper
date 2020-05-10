package com.barkov.ais.mywellbeinghelper.tabsswipe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.barkov.ais.mywellbeinghelper.DataBaseHelper;
import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.SettingsWizardActivity;
import com.barkov.ais.mywellbeinghelper.entity.Settings;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

public class EulaFragment extends ValidableFragment {

    protected Button btnAccountStep1;
    DataBaseHelper dbHelper;
    WebView webViewEula;
    boolean eulaagreed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_eula, container, false);

        eulaagreed = false;
        btnAccountStep1 = view.findViewById(R.id.btnAccept);

        btnAccountStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eulaagreed = true;
                ((SettingsWizardActivity) getActivity()).setEulaAgreed(eulaagreed);
                ((SettingsWizardActivity)getActivity()).nextTab();
            }
        });

        webViewEula = view.findViewById(R.id.wbViewEula);

        dbHelper = OpenHelperManager.getHelper(getActivity(), DataBaseHelper.class);
        showEula();

        int startPosition = (getActivity()).getIntent().getIntExtra("start_position", 0);
        if (startPosition > 0) {
            ((SettingsWizardActivity)getActivity()).setEulaAgreed(true);
            ((SettingsWizardActivity)getActivity()).nextTab();
        }
        return view;
    }

    protected boolean showEula()
    {

        RuntimeExceptionDao<Settings, Integer> settingsDao = dbHelper.getSettingsRuntimeDao();

        List<Settings> settings = settingsDao
                .queryForEq("key", Settings.EULA_KEY);

        String eulaText = "There should be an EULA text";
        if (settings.size() > 0) {
            eulaText = settings.get(0).getValue();
        }

        webViewEula.loadData(eulaText,"text/html", "utf-8");
        return true;
    }

    @Override
    public boolean validateInput()
    {
        return eulaagreed;
    }
}
