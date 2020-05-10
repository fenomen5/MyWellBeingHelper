package com.barkov.ais.mywellbeinghelper;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.barkov.ais.mywellbeinghelper.dbtools.DbInitScript;
import com.barkov.ais.mywellbeinghelper.entity.BodyParam;
import com.barkov.ais.mywellbeinghelper.entity.BodyParamType;
import com.barkov.ais.mywellbeinghelper.entity.City;
import com.barkov.ais.mywellbeinghelper.entity.Login;
import com.barkov.ais.mywellbeinghelper.entity.Settings;
import com.barkov.ais.mywellbeinghelper.entity.SportEvent;
import com.barkov.ais.mywellbeinghelper.entity.User;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClub;
import com.barkov.ais.mywellbeinghelper.entity.WellnessClubGallery;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String METABASE_NAME = "mywb_meta.db";
    private static final String DATABASE_NAME = "wellbeingapp.db";
    private static final String DB_PATH = "/data/data/com.barkov.ais.mywellbeinghelper/databases/";
    private static final int DATABASE_VERSION = 1;

    private Context context = null;

    private Dao<Settings, Integer> settingsDao = null;
    private RuntimeExceptionDao<Settings, Integer> settingsRuntimeDao = null;
    private RuntimeExceptionDao<City, Integer> CityRuntimeDao = null;
    private RuntimeExceptionDao<BodyParamType, Integer> getBodyParamTypeRuntimeDao = null;
    private RuntimeExceptionDao<BodyParam, Integer> getBodyParamRuntimeDao = null;
    private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;
    private RuntimeExceptionDao<Login, Integer> loginRuntimeDao = null;
    private RuntimeExceptionDao<WellnessClub, Integer> clubRuntimeDao = null;
    private RuntimeExceptionDao<WellnessClubGallery, Integer> galleryRuntimeDao = null;
    private RuntimeExceptionDao<SportEvent, Integer> getSportEventRuntimeDao = null;

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context =  context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
            createDatabaseFromScript(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        onCreate(sqLiteDatabase,connectionSource);
    }

    public Dao<Settings, Integer> getSettingsDao()
    {
        if (settingsDao == null) {
            try {
                settingsDao = getDao(Settings.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return settingsDao;
    }

    public RuntimeExceptionDao<Settings, Integer> getSettingsRuntimeDao()
    {
         if (settingsRuntimeDao == null) {
             settingsRuntimeDao = getRuntimeExceptionDao(Settings.class);
         }

         return settingsRuntimeDao;
    }

    public RuntimeExceptionDao<City, Integer> getCitiesRuntimeDao()
    {
        if (CityRuntimeDao == null) {
            CityRuntimeDao = getRuntimeExceptionDao(City.class);
        }

        return CityRuntimeDao;
    }

    public RuntimeExceptionDao<User, Integer> getUserRuntimeDao()
    {
        if (userRuntimeDao == null) {
            userRuntimeDao = getRuntimeExceptionDao(User.class);
        }

        return userRuntimeDao;
    }

    public RuntimeExceptionDao<Login, Integer> getLoginRuntimeDao()
    {
        if (loginRuntimeDao == null) {
            loginRuntimeDao = getRuntimeExceptionDao(Login.class);
        }

        return loginRuntimeDao;
    }

    public RuntimeExceptionDao<WellnessClub, Integer> getClubRuntimeDao()
    {
        if (clubRuntimeDao == null) {
            clubRuntimeDao = getRuntimeExceptionDao(WellnessClub.class);
        }

        return clubRuntimeDao;
    }

    public RuntimeExceptionDao<WellnessClubGallery, Integer> getGalleryRuntimeDao()
    {
        if (galleryRuntimeDao == null) {
            galleryRuntimeDao = getRuntimeExceptionDao(WellnessClubGallery.class);
        }

        return galleryRuntimeDao;
    }

    public RuntimeExceptionDao<BodyParamType, Integer> getBodyParamTypeRuntimeDao()
    {
        if (getBodyParamTypeRuntimeDao == null) {
            getBodyParamTypeRuntimeDao = getRuntimeExceptionDao(BodyParamType.class);
        }

        return getBodyParamTypeRuntimeDao;
    }

    public RuntimeExceptionDao<BodyParam, Integer> getBodyParamRuntimeDao()
    {
        if (getBodyParamRuntimeDao == null) {
            getBodyParamRuntimeDao = getRuntimeExceptionDao(BodyParam.class);
        }

        return getBodyParamRuntimeDao;
    }

    public RuntimeExceptionDao<SportEvent, Integer> getSportEventRuntimeDao()
    {
        if (getSportEventRuntimeDao == null) {
            getSportEventRuntimeDao = getRuntimeExceptionDao(SportEvent.class);
        }

        return getSportEventRuntimeDao;
    }

    /**
     * Create database by copying existed one
     * @throws IOException
     */
    /*public void createDatabase() throws IOException
    {
        boolean dbExist = checkDataBase();

        if(!dbExist){

            this.getReadableDatabase();
            SQLiteDatabase db;
            try {
                copyDataBase();
                String myPath = DB_PATH + DATABASE_NAME;
                db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
                onCreate(db, connectionSource);
                db.close();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }*/

    /**
     * Creating database from SQL script
     * @param db SQLiteDatabase
     */
    public void createDatabaseFromScript(SQLiteDatabase db)
    {
        String[] queries = DbInitScript.getInitScript(context).split(";");
        for (String query:queries) {
            db.execSQL(query);
        }
    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        InputStream myInput = context.getAssets().open(METABASE_NAME);

        String outFileName = DB_PATH + DATABASE_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
