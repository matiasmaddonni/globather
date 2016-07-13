package com.example.mmadddoni.globather.Service;

import com.example.mmadddoni.globather.Model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mmadddoni on 11/07/16.
 */
public interface OpenWeatherService {
    @GET("forecast/daily")
    Call<Response> getForecasts(@Query("q") String query, @Query("cnt") int count, @Query("APPID") String appId);
}
