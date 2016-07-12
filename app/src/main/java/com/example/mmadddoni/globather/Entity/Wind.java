package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Wind {
    @SerializedName("speed")
    private float speed;

    @SerializedName("deg")
    private float direction;

    public Wind() {
    }

    public Wind(float speed, float direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }
}
