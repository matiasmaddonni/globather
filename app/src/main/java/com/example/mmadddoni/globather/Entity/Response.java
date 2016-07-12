package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Response {
    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private List<Forecast> list;

    public Response() {
    }

    public Response(City city, List<Forecast> list) {
        this.city = city;
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }
}
