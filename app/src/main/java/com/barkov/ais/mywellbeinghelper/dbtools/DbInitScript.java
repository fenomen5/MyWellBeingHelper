package com.barkov.ais.mywellbeinghelper.dbtools;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DbInitScript {

    static String SCRIPT_FILE_NAME = "mywb_meta.sql";

    public static String getInitScript(Context context) {

        String script = "";
        try {
            InputStream myInput = context.getAssets().open(SCRIPT_FILE_NAME);

            BufferedReader insertReader = new BufferedReader(new InputStreamReader(myInput));

            while (insertReader.ready()) {
                script += insertReader.readLine();
            }
            insertReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return script;
    }
}
