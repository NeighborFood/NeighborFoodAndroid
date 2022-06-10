package com.epfl.neighborfood.neighborfoodandroid.services.notification;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.services.location.LocationService;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class LocationServiceTestImplementation extends LocationService {
    private PickupLocation location = new PickupLocation(-33.8523341, 151.2106085);
    @Override
    public Task<PickupLocation> getDeviceLocation() {
        pickupLocationMutableLiveData.postValue(location);
        return Tasks.forResult(location);
    }
    public void setLocation(PickupLocation location){
        this.location = location;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
