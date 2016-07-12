package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Weather implements Serializable{
    @SerializedName("id")
    private int id;

    @SerializedName("main")
    private String status;

    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String icon;

    public Weather() {
    }

    public Weather(int id, String status, String description, String icon) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
