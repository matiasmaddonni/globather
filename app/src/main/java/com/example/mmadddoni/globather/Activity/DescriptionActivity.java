package com.example.mmadddoni.globather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mmadddoni.globather.Model.City;
import com.example.mmadddoni.globather.Model.Forecast;
import com.example.mmadddoni.globather.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
            city = Parcels.unwrap(getIntent().getParcelableExtra(INTENT_CITY));
            forecast = Parcels.unwrap(getIntent().getParcelableExtra(INTENT_FORECAST));
        } else {
            city = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_CITY));
            forecast = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_FORECAST));
        }

        loadUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SAVED_CITY, Parcels.wrap(City.class, city));
        outState.putParcelable(SAVED_FORECAST, Parcels.wrap(Forecast.class, forecast));
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
        ((TextView)findViewById(R.id.temp_day)).setText(getResources().getString(R.string.description_temp_actual) + new DecimalFormat("##.#").format(forecast.getTemp().getValueDay() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_min)).setText(getResources().getString(R.string.description_temp_min) + new DecimalFormat("##.#").format(forecast.getTemp().getValueMin() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_max)).setText(getResources().getString(R.string.description_temp_max) + new DecimalFormat("##.#").format(forecast.getTemp().getValueMax() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_night)).setText(getResources().getString(R.string.description_temp_night) + new DecimalFormat("##.#").format(forecast.getTemp().getValueNight() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_eve)).setText(getResources().getString(R.string.description_temp_eve) + new DecimalFormat("##.#").format(forecast.getTemp().getValueEve() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_morn)).setText(getResources().getString(R.string.description_temp_morn) + new DecimalFormat("##.#").format(forecast.getTemp().getValueMorn() - 273.16) + "°C");
        ((TextView)findViewById(R.id.temp_press)).setText(String.valueOf(forecast.getPressure()) + " hPa");
        ((TextView)findViewById(R.id.temp_hum)).setText(String.valueOf(forecast.getHumidity()) + "%");
    }
}
