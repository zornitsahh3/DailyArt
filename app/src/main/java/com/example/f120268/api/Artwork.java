package com.example.f120268.api;

import com.google.gson.annotations.SerializedName;

public class Artwork {
    @SerializedName("objectID")
    private Integer objectID;
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("artistDisplayName")
    private String artistDisplayName;
    
    @SerializedName("primaryImage")
    private String primaryImage;
    
    @SerializedName("primaryImageSmall")
    private String primaryImageSmall;
    
    @SerializedName("objectDate")
    private String objectDate;
    
    @SerializedName("department")
    private String department;
    
    @SerializedName("culture")
    private String culture;
    
    @SerializedName("period")
    private String period;

    public Integer getObjectID() {
        return objectID;
    }

    public String getTitle() {
        return title != null ? title : "Unknown Title";
    }

    public String getArtistDisplayName() {
        return artistDisplayName != null ? artistDisplayName : "Unknown Artist";
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public String getPrimaryImageSmall() {
        return primaryImageSmall;
    }

    public String getObjectDate() {
        return objectDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getCulture() {
        return culture;
    }

    public String getPeriod() {
        return period;
    }
}

