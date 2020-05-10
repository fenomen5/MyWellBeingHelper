package com.barkov.ais.mywellbeinghelper.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbGalleryItem")
public class WellnessClubGallery {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String image_path;
    @DatabaseField
    private Integer gallery_id;

    public WellnessClubGallery()
    {

    }

    public WellnessClubGallery(String name, String image_path, Integer gallery_id) {
        this.name = name;
        this.image_path = image_path;
        this.gallery_id = gallery_id;
    }

    @Override
    public String toString() {
        return "WellnessClubGallery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_path='" + image_path + '\'' +
                ", gallery_id=" + gallery_id +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPath()
    {
        return image_path;
    }

    public int getGalleryId()
    {
        return gallery_id;
    }
}
