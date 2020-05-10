package com.barkov.ais.mywellbeinghelper.dbtools;

import android.util.Log;

import com.barkov.ais.mywellbeinghelper.BodyParamsActivity;
import com.barkov.ais.mywellbeinghelper.entity.BodyParamType;
import com.barkov.ais.mywellbeinghelper.entity.City;
import com.barkov.ais.mywellbeinghelper.entity.Country;
import com.barkov.ais.mywellbeinghelper.entity.Login;
import com.barkov.ais.mywellbeinghelper.entity.Settings;
import com.barkov.ais.mywellbeinghelper.entity.SportEvent;
import com.barkov.ais.mywellbeinghelper.entity.User;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClub;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClubGallery;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;

public class DataBaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[]{
            User.class,
            Settings.class,
            Country.class,
            City.class,
            BodyParamType.class,
            Login.class,
            WellnessClub.class,
            WellnessClubGallery.class,
            SportEvent.class
    };

    public static void main(String[] args)
    {
        try {
            writeConfigFile("ormlite_config.txt", classes);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
