package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbBodyParamType")
public class BodyParamType {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String name;

    public BodyParamType()
    {

    }

    public BodyParamType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BodyParamType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
