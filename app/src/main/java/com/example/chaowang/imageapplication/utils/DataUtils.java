package com.example.chaowang.imageapplication.utils;

public class DataUtils {

    private String id;

    private String imageUrl;

    private String title;

    private String director;

    private String casts;

    private String year;

    private String subType;

    private static DataUtils instance = null;


    public static DataUtils getInstance()
    {

        if(null == instance)
        {
            synchronized (DataUtils.class)
            {
                if(null == instance)
                {
                    instance = new DataUtils();
                }
            }
        }
        return instance;
    }

    public void setNull()
    {
        instance = null;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
