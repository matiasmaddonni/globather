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
    private double lon;

    @SerializedName("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
