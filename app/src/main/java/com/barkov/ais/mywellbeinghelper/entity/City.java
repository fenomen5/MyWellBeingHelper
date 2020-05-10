package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbCity")
public class City {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int country_id;

    public City()
    {

    }

    public City(String name, int country_id) {
        this.name = name;
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country_id=" + country_id +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
