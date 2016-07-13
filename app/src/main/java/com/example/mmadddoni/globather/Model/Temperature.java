package com.example.mmadddoni.globather.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.TemperatureRealmProxy;

/**
 * Created by mmadddoni on 11/07/16.
 */
@Parcel(implementations = { TemperatureRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Temperature.class })
public class Temperature extends RealmObject {
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
