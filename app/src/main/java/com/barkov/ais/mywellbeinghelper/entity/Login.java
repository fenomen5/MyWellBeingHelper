package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbLogin")
public class Login {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String login;
    @DatabaseField
    private String password;
    @DatabaseField
    private String user_id;

    public Login()
    {

    }

    public Login(String login, String password, String user_id) {
        this.login = login;
        this.password = password;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public boolean checkLogin(String login, String password)
    {
        if (this.login.equals(login) && this.password.equals(password)) {
            return true;
        }

        return false;
    }
}
