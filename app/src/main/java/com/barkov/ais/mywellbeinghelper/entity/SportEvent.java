package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbEvent")
public class SportEvent {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private int event_type;
    @DatabaseField
    private String description;
    @DatabaseField
    private String address;
    @DatabaseField
    private Float longitude;
    @DatabaseField
    private Float latitude;
    @DatabaseField
    private String date_start;
    @DatabaseField
    private Integer gallery_id;
    @DatabaseField
    private Integer city_id;

    public SportEvent()
    {

    }

    public SportEvent(String title, int event_type, String description, String address, Float longitude, Float latitude, String date_start, Integer gallery_id, Integer city_id) {
        this.title = title;
        this.event_type = event_type;
        this.description = description;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date_start = date_start;
        this.gallery_id = gallery_id;
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        return "SportEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", event_type=" + event_type +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", date_start='" + date_start + '\'' +
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

    public String getStartDate()
    {
        return String.valueOf(date_start);
    }

    public Integer getGalleryId()
    {
        return gallery_id;
    }

    public String getDescription() {
        return description;
    }
}
