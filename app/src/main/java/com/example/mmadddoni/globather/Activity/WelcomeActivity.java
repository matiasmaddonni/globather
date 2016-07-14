package com.example.mmadddoni.globather.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mmadddoni.globather.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by mmadddoni on 12/07/16.
 */
public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        googleApiClient = new GoogleApiClient.Builder(this, this, this)
                .addApi(LocationServices.API)
                .build();

        setUpUI();
    }

    private void startClient() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
        super.onStop();
    }

    private void setUpUI() {
        Button location = (Button) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(WelcomeActivity.this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, PERMISSION_ACCESS_COARSE_LOCATION);
                } else {
                    startClient();
                }
            }
        });

        Button search = (Button) findViewById(R.id.button_welcome);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = (EditText) findViewById(R.id.edit_welcome);
                String selected = edit.getText().toString();
                if (!selected.isEmpty()) {
                    callMainActivity(selected);
                } else {
                    showAlertDialog("Please write a valid location");
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startClient();
                } else {
                    showAlertDialog("We need your location");
                }

                break;
        }
    }

    private void callMainActivity(String city) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.MODE, MainActivity.MODE_CITY);
        intent.putExtra(MainActivity.WELCOME_CITY, city);
        startActivity(intent);
    }

    private void callMainActivity(Double lat, Double lon) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.MODE, MainActivity.MODE_LOCATION);
        intent.putExtra(MainActivity.WELCOME_LAT, lat);
        intent.putExtra(MainActivity.WELCOME_LON, lon);
        startActivity(intent);
    }

    private void showAlertDialog(String message) {
        new SweetAlertDialog(WelcomeActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Alert!")
                .setContentText(message)
                .setConfirmText("Ok, i got it!")
                .show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

            callMainActivity(lastLocation.getLatitude(), lastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("GoogleAPIClient", "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("GoogleAPIClient", "Connection Failed");
    }
}
