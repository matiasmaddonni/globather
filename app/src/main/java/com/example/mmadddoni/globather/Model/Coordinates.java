package com.example.mmadddoni.globather.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.CoordinatesRealmProxy;
import io.realm.RealmObject;

/**
 * Created by mmadddoni on 11/07/16.
 */
@Parcel(implementations = { CoordinatesRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Coordinates.class })
public class Coordinates extends RealmObject {
    @SerializedName("lon")
    private float lon;

    @SerializedName("lat")
    private float lat;

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
