package com.example.mmadddoni.globather.Converter;

import android.os.Parcel;

import com.example.mmadddoni.globather.Model.Weather;

import org.parceler.Parcels;

/**
 * Created by mmadddoni on 12/07/16.
 */
public class WeatherListParcelConverter extends RealmListParcelConverter<Weather> {

    @Override
    public void itemToParcel(Weather input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Weather itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Weather.class.getClassLoader()));
    }
}