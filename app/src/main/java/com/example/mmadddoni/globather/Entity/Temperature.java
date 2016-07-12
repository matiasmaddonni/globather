package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Temperature implements Serializable{
    @SerializedName("day")
    private float valueDay;

    @SerializedName("min")
    private float valueMin;

    @SerializedName("max")
    private float valueMax;

    @SerializedName("night")
    private float valueNight;

    @SerializedName("eve")
    private float valueEve;

    @SerializedName("morn")
    private float valueMorn;

    public Temperature() {
    }

    public Temperature(float valueDay, float valueMin, float valueMax, float valueNight, float valueEve, float valueMorn) {
        this.valueDay = valueDay;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.valueNight = valueNight;
        this.valueEve = valueEve;
        this.valueMorn = valueMorn;
    }

    public float getValueDay() {
        return valueDay;
    }

    public void setValueDay(float valueDay) {
        this.valueDay = valueDay;
    }

    public float getValueMin() {
        return valueMin;
    }

    public void setValueMin(float valueMin) {
        this.valueMin = valueMin;
    }

    public float getValueMax() {
        return valueMax;
    }

    public void setValueMax(float valueMax) {
        this.valueMax = valueMax;
    }

    public float getValueNight() {
        return valueNight;
    }

    public void setValueNight(float valueNight) {
        this.valueNight = valueNight;
    }

    public float getValueEve() {
        return valueEve;
    }

    public void setValueEve(float valueEve) {
        this.valueEve = valueEve;
    }

    public float getValueMorn() {
        return valueMorn;
    }

    public void setValueMorn(float valueMorn) {
        this.valueMorn = valueMorn;
    }
}
