package com.example.mmadddoni.globather.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.CityRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mmadddoni on 11/07/16.
 */
@Parcel(implementations = { CityRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { City.class })
public class City extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("coord")
    private Coordinates coord;

    @SerializedName("country")
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
