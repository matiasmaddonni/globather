package com.example.mmadddoni.globather.Model;

import com.example.mmadddoni.globather.Converter.WeatherListParcelConverter;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.ForecastRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by mmadddoni on 11/07/16.
 */
@Parcel(implementations = { ForecastRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Forecast.class })
public class Forecast extends RealmObject {
    @SerializedName("dt")
    private long time;

    @SerializedName("temp")
    private Temperature temp;

    @SerializedName("pressure")
    private float pressure;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("weather")
    private RealmList<Weather> weather;

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

    public RealmList<Weather> getWeather() {
        return weather;
    }

    @ParcelPropertyConverter(WeatherListParcelConverter.class)
    public void setWeather(RealmList<Weather> weather) {
        this.weather = weather;
    }
}
