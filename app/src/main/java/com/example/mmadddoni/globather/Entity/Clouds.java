package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Clouds {
    @SerializedName("all")
    private int cloudiness;

    public Clouds() {
    }

    public Clouds(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }
}
