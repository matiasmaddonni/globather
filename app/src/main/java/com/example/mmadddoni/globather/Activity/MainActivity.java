package com.example.mmadddoni.globather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mmadddoni.globather.Adapter.ForecastAdapter;
import com.example.mmadddoni.globather.Adapter.ForecastAdapterDecorator;
import com.example.mmadddoni.globather.Entity.Response;
import com.example.mmadddoni.globather.R;
import com.example.mmadddoni.globather.Service.OpenWeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mmadddoni on 11/07/16.
 */
public class MainActivity extends AppCompatActivity {
    private static final int CITY_ID = 3427833;
    private static final int DAYS_REQUESTED = 15;
    private static final String APP_ID = "1a0fc8a04f53b87f9f057ffb763a435a";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private ForecastAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        loadForecastData();
    }

    private void loadForecastData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherService service = retrofit.create(OpenWeatherService.class);

        Call<Response> responseCall = service.getForecasts(CITY_ID, DAYS_REQUESTED, APP_ID);

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mAdapter = new ForecastAdapter(MainActivity.this, response.body().getCity(), response.body().getList());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new ForecastAdapterDecorator(MainActivity.this, LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("Info", "Failure calling rest service");
            }
        });
    }
}
