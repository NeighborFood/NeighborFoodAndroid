package com.epfl.neighborfood.neighborfoodandroid.services.location;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class GoogleLocationService extends LocationService implements LocationListener {
    private FusedLocationProviderClient locationProvider;

    public GoogleLocationService() {
        super();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        pickupLocationMutableLiveData.postValue(new PickupLocation(location.getLatitude(), location.getLongitude()));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //already performing the check in the getLocationPermissionGranted call
    @SuppressLint("MissingPermission")
    @Override
    public Task<PickupLocation> getDeviceLocation() {
        if (locationProvider == null) {
            locationProvider = LocationServices.getFusedLocationProviderClient(NeighborFoodApplication.getAppContext());
        }
        if (!getLocationPermissionGranted()) {
            return Tasks.forException(new IllegalStateException("Permission not granted yet!"));
        }
        Task<PickupLocation> res = locationProvider.getLastLocation().continueWith(t -> {
            if (t.isSuccessful() && t.getResult() != null) {
                return new PickupLocation(t.getResult().getLatitude(), t.getResult().getLongitude());
            }
            return null;
        });
        res.addOnSuccessListener(pickupLocationMutableLiveData::postValue);
        return res;
    }
}
