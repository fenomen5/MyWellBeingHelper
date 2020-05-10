package com.barkov.ais.mywellbeinghelper;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.support.design.widget.TabLayout;

import com.barkov.ais.mywellbeinghelper.entity.Login;
import com.barkov.ais.mywellbeinghelper.entity.Settings;
import com.barkov.ais.mywellbeinghelper.entity.User;
import com.barkov.ais.mywellbeinghelper.tabsswipe.adapter.SectionsPageAdapter;
import com.barkov.ais.mywellbeinghelper.tabsswipe.fragment.EmailConfirmationFragment;
import com.barkov.ais.mywellbeinghelper.tabsswipe.fragment.EulaFragment;
import com.barkov.ais.mywellbeinghelper.tabsswipe.fragment.SelectPlaceFragment;
import com.barkov.ais.mywellbeinghelper.tabsswipe.fragment.UserCredentialsFragment;
import com.barkov.ais.mywellbeinghelper.tabsswipe.fragment.UserPersonalFragment;
import com.barkov.ais.mywellbeinghelper.tabsswipe.fragment.ValidableFragment;
import com.barkov.ais.mywellbeinghelper.tabsswipe.pager.ctrViewPager;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.field.types.DateTimeType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SettingsWizardActivity extends FragmentActivity {

    private boolean eulaAgreed;
    private int mCode;
    SectionsPageAdapter mSectionsPageAdapter;
    TabLayout  mTableLayout;
    private ctrViewPager mviewPager;
    int startPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_wizard);
        Log.d("dbg", "onCreate: starting");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mviewPager = findViewById(R.id.container);
        setupViewPager(mviewPager);

        mTableLayout = findViewById(R.id.tabs);
        mTableLayout.setupWithViewPager(mviewPager,true);

        mCode = 0;
    }

    private void setupViewPager(ViewPager viewPager)
    {
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mSectionsPageAdapter.addFragment(new EulaFragment(), "EULA");
        mSectionsPageAdapter.addFragment(new UserPersonalFragment(), "Personal");
        mSectionsPageAdapter.addFragment(new EmailConfirmationFragment(), "Email");
        mSectionsPageAdapter.addFragment(new SelectPlaceFragment(), "Location");
        mSectionsPageAdapter.addFragment(new UserCredentialsFragment(), "Creds");
        viewPager.setAdapter(mSectionsPageAdapter);
        setTabsState(this.eulaAgreed);
    }

    public void setEulaAgreed(boolean isAgreed)
    {
        this.eulaAgreed = isAgreed;
        setTabsState(this.eulaAgreed);
    }

    public void nextTab()
    {
        TabLayout layout = findViewById(R.id.tabs);

        int position = layout.getSelectedTabPosition();
        if (position + 1 < layout.getTabCount()) {
            layout.getTabAt(position + 1).select();
        }

    }

    public void setVerificationCode (int code)
    {
        this.mCode = code;
    }

    public int getVerificationCode ()
    {
        return this.mCode;
    }

    public void setTabsState(final boolean state)
    {
        mviewPager.setSwipeState(state);

        TabLayout layout = findViewById(R.id.tabs);
        final TabLayout lt = layout;
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (!eulaAgreed) {
                    lt.getTabAt(0).select();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public int registerUser()
    {
        int result = validateProfileSettings();

        if (result >= 0) {
            TabLayout layout = findViewById(R.id.tabs);
            layout.getTabAt(result).select();
        }

        createProfile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return result;
    }

    protected int validateProfileSettings()
    {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (!((ValidableFragment)getSupportFragmentManager()
                    .getFragments().get(i)).validateInput()
            ) {
                return i;
            }
        }

        return 0;
    }

    protected void createProfile()
    {
        ArrayMap profile = new ArrayMap();

        for (int i=0; i< getSupportFragmentManager().getFragments().size(); i++) {

            ((ValidableFragment)getSupportFragmentManager()
                    .getFragments().get(i)).setProfileFields(profile);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        User user = new User(profile.get("name").toString(),
                profile.get("surname").toString(),
                profile.get("city").toString(),
                dateFormat.format(date),
                profile.get("email").toString()

        );

        DataBaseHelper dbHelper;
        dbHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);

        RuntimeExceptionDao userDao = dbHelper.getUserRuntimeDao();

        int userId = userDao.create(user);

        RuntimeExceptionDao loginDao = dbHelper.getLoginRuntimeDao();

        try {
            loginDao.create(new Login(profile.get("login").toString(),
                    profile.get("password").toString(),
                    String.valueOf(userId)));

            RuntimeExceptionDao settingsDao = dbHelper.getSettingsRuntimeDao();
            List<Settings> settings = settingsDao.queryForEq("key","first_run");
            settings.get(0).setValue("false");
            settingsDao.update(settings.get(0));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
