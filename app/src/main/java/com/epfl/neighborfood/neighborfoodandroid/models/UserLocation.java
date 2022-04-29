package com.epfl.neighborfood.neighborfoodandroid.models;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity;

import java.io.Serializable;

public class UserLocation implements Serializable {
    private double longitude;
    private double latitude;
    private LocationManager locationManager;

    public UserLocation(LocationManager manager, AppCompatActivity main){
        locationManager = manager;

        if(ContextCompat.checkSelfPermission(main, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(main, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(main , new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location){
                setLongitude(location.getLongitude());
                setLatitude(location.getLatitude());
            }
        });
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
