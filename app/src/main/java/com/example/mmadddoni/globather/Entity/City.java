package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class City implements Serializable{
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("coord")
    private Coordinates coord;

    @SerializedName("country")
    private String country;

    public City() {
    }

    public City(int id, String name, Coordinates coord, String country) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
    }

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
