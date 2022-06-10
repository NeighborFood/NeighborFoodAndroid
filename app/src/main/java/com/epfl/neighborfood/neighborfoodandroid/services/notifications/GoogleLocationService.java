package com.epfl.neighborfood.neighborfoodandroid.services.notifications;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;

public class GoogleLocationService extends LocationService implements LocationListener {
    private boolean locationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Location lastKnownLocation;
    private static final LatLng DEFAULT_LOCATION = new LatLng(-33.8523341, 151.2106085);

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lastKnownLocation = location;
    }

    public Location getLastKnownLocation(){
        if(lastKnownLocation == null){
            return new Location(DEFAULT_LOCATION.latitude,DEFAULT_LOCATION.longitude);
        }
        return lastKnownLocation;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
