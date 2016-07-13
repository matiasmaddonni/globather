package com.example.mmadddoni.globather.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Response extends RealmObject {
    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private RealmList<Forecast> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Forecast> getList() {
        return list;
    }

    public void setList(RealmList<Forecast> list) {
        this.list = list;
    }
}
