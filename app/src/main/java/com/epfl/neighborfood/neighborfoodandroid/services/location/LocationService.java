package com.epfl.neighborfood.neighborfoodandroid.services.location;

import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.google.android.gms.tasks.Task;

/**
 * Provides the location services utilities
 */
public abstract class LocationService extends Service {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    protected final MutableLiveData<PickupLocation> pickupLocationMutableLiveData;

    protected LocationService() {
        this.pickupLocationMutableLiveData = new MutableLiveData<>();
    }

    /**
     * gets the user's response to the location permission
     *
     * @return user's acceptance or denial of the location's permission
     */
    public boolean getLocationPermissionGranted() {
        return (ContextCompat.checkSelfPermission(NeighborFoodApplication.getAppContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * requests the user the location permission
     *
     * @param activity activity where the location permission is requested
     */
    public void requestLocationPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    /**
     * returns the live data of the pickup location
     *
     * @return live data of the pickup location
     */
    public LiveData<PickupLocation> getPickupLocationLiveData() {
        return pickupLocationMutableLiveData;
    }

    /**
     * gets the user's device current location
     *
     * @return a task containing the device location
     */
    public abstract Task<PickupLocation> getDeviceLocation();
}
