package com.example.mmadddoni.globather.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class Forecast implements Serializable {
    @SerializedName("dt")
    private long time;

    @SerializedName("temp")
    private Temperature temp;

    @SerializedName("pressure")
    private float pressure;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("weather")
    private List<Weather> weather;

    public Forecast() {
    }

    public Forecast(long time, Temperature temp, float pressure, int humidity, List<Weather> weather) {
        this.time = time;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
