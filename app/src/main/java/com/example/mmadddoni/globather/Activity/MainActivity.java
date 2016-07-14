package com.example.mmadddoni.globather.Activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.mmadddoni.globather.Adapter.ForecastAdapter;
import com.example.mmadddoni.globather.Adapter.ForecastAdapterDecorator;
import com.example.mmadddoni.globather.Model.Response;
import com.example.mmadddoni.globather.R;
import com.example.mmadddoni.globather.Service.OpenWeatherService;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class MainActivity extends AppCompatActivity {
    public static final int MODE_CITY = 1;
    public static final int MODE_LOCATION = 2;

    public static final String MODE = "MODE";
    public static final String WELCOME_CITY = "WELCOME_CITY";
    public static final String WELCOME_LAT = "WELCOME_LAT";
    public static final String WELCOME_LON = "WELCOME_LON";

    private static final int DAYS_REQUESTED = 15;
    private static final String APP_ID = "1a0fc8a04f53b87f9f057ffb763a435a";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private Toolbar mToolbar;

    private int mode;
    private double lat;
    private double lon;
    private String city;
    private RecyclerView recyclerView;
    private ForecastAdapter mAdapter;

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        realm = Realm.getDefaultInstance();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (savedInstanceState == null) {
            mode = getIntent().getIntExtra(MODE, 1);
            handleBundle(getIntent().getExtras());
        } else {
            mode = savedInstanceState.getInt(MODE);
            handleBundle(savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MODE, mode);
        outState.putString(WELCOME_CITY, city);
        outState.putDouble(WELCOME_LAT, lat);
        outState.putDouble(WELCOME_LON, lon);
        super.onSaveInstanceState(outState);
    }

    private void handleBundle(Bundle bundle) {
        switch (mode) {
            case MODE_CITY:
                city = bundle.getString(WELCOME_CITY);
                loadCityForecastData();
                break;
            case MODE_LOCATION:
                lat = bundle.getDouble(WELCOME_LAT);
                lon = bundle.getDouble(WELCOME_LON);
                loadLocationForecastData();
                break;
        }
    }

    private void loadCityForecastData() {
        long count = realm.where(Response.class).count();
        if (count != 0) {
            String realmCity = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            Response response = realm.where(Response.class).equalTo("city.name", realmCity).findFirst();
            if (response != null) {
                loadResponseFromRealm(response);
            } else {
                loadResponseFromRetrofit(mode);
            }
        } else {
            loadResponseFromRetrofit(mode);
        }
    }

    private void loadLocationForecastData() {
        long count = realm.where(Response.class).count();
        if (count != 0) {
            RealmResults<Response> responses = realm.where(Response.class).findAll();
            for (Response response : responses) {
                Location realmLocation = new Location("");
                realmLocation.setLatitude(response.getCity().getCoord().getLat());
                realmLocation.setLongitude(response.getCity().getCoord().getLon());

                Location gpsLocation = new Location("");
                gpsLocation.setLatitude(lat);
                gpsLocation.setLongitude(lon);

                float distance = realmLocation.distanceTo(gpsLocation);
                if (distance < 50000) {
                    loadResponseFromRealm(response);
                    return;
                }
            }

        }
        loadResponseFromRetrofit(mode);
    }

    private void loadResponseFromRealm(Response response) {
        Toast.makeText(MainActivity.this, "Showing data from Realm", Toast.LENGTH_LONG).show();
        setUpRecyclerView(response);
    }

    private void loadResponseFromRetrofit(int mode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherService service = retrofit.create(OpenWeatherService.class);

        Call<Response> responseCall;

        switch (mode) {
            case MODE_CITY:
                responseCall = service.getCityForecasts(city, DAYS_REQUESTED, APP_ID);
                break;
            case MODE_LOCATION:
                responseCall = service.getLocationForecasts(lat, lon, DAYS_REQUESTED, APP_ID);
                break;
            default:
                responseCall = service.getCityForecasts(city, DAYS_REQUESTED, APP_ID);
                break;
        }

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, final retrofit2.Response<Response> response) {
                realm.beginTransaction();
                realm.copyToRealm(response.body());
                realm.commitTransaction();

                Toast.makeText(MainActivity.this, "Showing data from Retrofit", Toast.LENGTH_LONG).show();
                setUpRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
                Log.i("Info", "Failure calling rest service");
            }
        });
    }

    private void setUpRecyclerView(Response response) {
        mAdapter = new ForecastAdapter(MainActivity.this, response.getCity(), response.getList());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ForecastAdapterDecorator(MainActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
}
