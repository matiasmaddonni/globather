package com.example.mmadddoni.globather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mmadddoni.globather.Entity.City;
import com.example.mmadddoni.globather.Entity.Forecast;
import com.example.mmadddoni.globather.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class DescriptionActivity extends AppCompatActivity {
    private static final String INTENT_CITY = "CITY";
    private static final String INTENT_FORECAST = "FORECAST";
    private static final String SAVED_CITY = "SAVED_CITY";
    private static final String SAVED_FORECAST = "SAVED_FORECAST";

    private Toolbar mToolbar;

    private City city;
    private Forecast forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            city = (City) getIntent().getSerializableExtra(INTENT_CITY);
            forecast = (Forecast) getIntent().getSerializableExtra(INTENT_FORECAST);
        } else {
            city = (City) savedInstanceState.getSerializable(SAVED_CITY);
            forecast = (Forecast) savedInstanceState.getSerializable(SAVED_FORECAST);
        }

        loadUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(SAVED_CITY, city);
        outState.putSerializable(SAVED_FORECAST, forecast);
        super.onSaveInstanceState(outState);
    }

    private void loadUI() {
        Picasso.with(this)
                .load("http://openweathermap.org/img/w/" + forecast.getWeather().get(0).getIcon() + ".png")
                .into(((ImageView)findViewById(R.id.weather_icon)));
        ((TextView)findViewById(R.id.city_name)).setText(city.getName());
        ((TextView)findViewById(R.id.city_country)).setText(city.getCountry());
        ((TextView)findViewById(R.id.city_lat)).setText(String.valueOf(city.getCoord().getLat()));
        ((TextView)findViewById(R.id.city_lon)).setText(String.valueOf(city.getCoord().getLon()));
        ((TextView)findViewById(R.id.city_date)).setText(new SimpleDateFormat("dd MMM yyyy HH:MM").format(forecast.getTime()*1000));
        ((TextView)findViewById(R.id.temp_day)).setText("Actual: " + new DecimalFormat("##.#").format(forecast.getTemp().getValueDay() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_min)).setText("Mínima: " + new DecimalFormat("##.#").format(forecast.getTemp().getValueMin() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_max)).setText("Máxima: " + new DecimalFormat("##.#").format(forecast.getTemp().getValueMax() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_night)).setText("Noche: " + new DecimalFormat("##.#").format(forecast.getTemp().getValueNight() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_eve)).setText("Atardecer: " + new DecimalFormat("##.#").format(forecast.getTemp().getValueEve() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_morn)).setText("Mañana: " + new DecimalFormat("##.#").format(forecast.getTemp().getValueMorn() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_press)).setText(String.valueOf(forecast.getPressure()) + " hPa");
        ((TextView)findViewById(R.id.temp_hum)).setText(String.valueOf(forecast.getHumidity()) + "%");
    }
}
