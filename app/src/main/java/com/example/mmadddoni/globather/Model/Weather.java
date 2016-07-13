package com.example.mmadddoni.globather.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.WeatherRealmProxy;

/**
 * Created by mmadddoni on 11/07/16.
 */
@Parcel(implementations = { WeatherRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Weather.class })
public class Weather extends RealmObject {
    @SerializedName("id")
    private int id;

    @SerializedName("main")
    private String status;

    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String icon;

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
