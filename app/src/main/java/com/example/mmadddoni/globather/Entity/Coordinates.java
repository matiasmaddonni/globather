package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Coordinates implements Serializable{
    @SerializedName("lon")
    private float lon;

    @SerializedName("lat")
    private float lat;

    public Coordinates() {
    }

    public Coordinates(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
