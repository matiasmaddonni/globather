package com.example.mmadddoni.globather.Activity;

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

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class MainActivity extends AppCompatActivity {
    private static final String WELCOME_CITY = "WELCOME_CITY";
    private static final String SAVED_CITY = "SAVED_CITY";

    private static final int DAYS_REQUESTED = 15;
    private static final String APP_ID = "1a0fc8a04f53b87f9f057ffb763a435a";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private Toolbar mToolbar;

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
            city = getIntent().getStringExtra(WELCOME_CITY);
        } else {
            city = savedInstanceState.getString(SAVED_CITY);
        }

        loadForecastData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVED_CITY, city);
        super.onSaveInstanceState(outState);
    }

    private void loadForecastData() {
        long count = realm.where(Response.class).count();
        if (count != 0) {
            Response response = realm.where(Response.class).equalTo("city.name", city).findFirst();
            if (response != null) {
                loadResponseFromRealm(response);
            } else {
                loadResponseFromRetrofit();
            }
        } else {
            loadResponseFromRetrofit();
        }
    }

    private void loadResponseFromRealm(Response response) {
        Toast.makeText(MainActivity.this, "Showing data from Realm", Toast.LENGTH_LONG).show();
        setUpRecyclerView(response);
    }

    private void loadResponseFromRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherService service = retrofit.create(OpenWeatherService.class);

        Call<Response> responseCall = service.getForecasts(city, DAYS_REQUESTED, APP_ID);

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
                //TODO: show dialog
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
