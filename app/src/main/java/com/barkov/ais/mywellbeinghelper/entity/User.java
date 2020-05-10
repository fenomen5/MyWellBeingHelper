package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbUser")
public class User {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String first_name;
    @DatabaseField
    private String second_name;
    @DatabaseField
    private String city_id;
    @DatabaseField
    private String first_login;
    @DatabaseField
    private String email;

    public User()
    {

    }

    public User(String first_name, String second_name, String city_id, String first_login, String email) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.city_id = city_id;
        this.first_login = first_login;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", city_id='" + city_id + '\'' +
                ", first_login='" + first_login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
