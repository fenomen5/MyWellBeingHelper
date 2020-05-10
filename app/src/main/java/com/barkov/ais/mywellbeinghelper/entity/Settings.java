package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbSettings")
public class Settings {

    public static final String FIRST_TIME_RUN_KEY = "first_run";
    public static final String EULA_KEY = "eula";

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String key;
    @DatabaseField
    private String value;

    public Settings()
    {

    }

    public Settings(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }


}
