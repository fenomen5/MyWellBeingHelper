package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbWellnessClub")
public class WellnessClub {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private float rating;
    @DatabaseField
    private String description;
    @DatabaseField
    private String address;
    @DatabaseField
    private Float longitude;
    @DatabaseField
    private Float latitude;
    @DatabaseField
    private Float open_hour;
    @DatabaseField
    private Float close_hour;
    @DatabaseField
    private Integer gallery_id;
    @DatabaseField
    private Integer city_id;

    public WellnessClub()
    {

    }

    public WellnessClub(String title, float rating, String description, String address, Float longitude, Float latitude, Float open_hour, Float close_hour, Integer gallery_id, Integer city_id) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.open_hour = open_hour;
        this.close_hour = close_hour;
        this.gallery_id = gallery_id;
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        return "WellnessClub{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", open_hour=" + open_hour +
                ", close_hour=" + close_hour +
                ", gallery_id=" + gallery_id +
                ", city_id=" + city_id +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public Float getRating()
    {
        return Float.valueOf(rating);
    }

    public String getAddress()
    {
        return address;
    }

    public Float getLatitude()
    {
        return latitude;
    }

    public Float getLongitude()
    {
        return longitude;
    }

    public String getWorkingHours()
    {
        String result = "";
        int hours  = open_hour.intValue();
        float minutes  = open_hour - hours;

        result += String.format("%2s", "" + hours).replace(' ', '0');
        result += ":";
        result += String.format("%2s", "" + Math.round(minutes * 100)).replace(' ', '0');
        result += " - ";
        hours  = close_hour.intValue();
        minutes  = close_hour - hours;

        result += String.format("%2s", "" + hours).replace(' ', '0');
        result += ":";
        result += String.format("%2s", "" + Math.round(minutes * 100)).replace(' ', '0');

        return result;
    }

    public Integer getGalleryId()
    {
        return gallery_id;
    }

    public String getDescription() {
        return description;
    }
}
