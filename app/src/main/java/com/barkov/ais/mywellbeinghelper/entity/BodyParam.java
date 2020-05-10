package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbBodyParam")
public class BodyParam {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private Integer param_type;
    @DatabaseField
    private float value;
    @DatabaseField
    private String created_at;

    public BodyParam()
    {

    }

    public BodyParam(Integer param_type, float value, String created_at) {
        this.param_type = param_type;
        this.value = value;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "BodyParam{" +
                "id=" + id +
                ", param_type=" + param_type +
                ", value=" + value +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public float getValue() {
        return value;
    }

    public Integer getParam_type() {
        return param_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
