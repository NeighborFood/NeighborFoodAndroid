package com.epfl.neighborfood.neighborfoodandroid.models;

import android.location.Location;

public class PickupLocation extends Model {
    private double latitude;
    private double longitude;

    public PickupLocation(){}
    public PickupLocation(Location location){
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
    public PickupLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static float distanceBetweenLocations(PickupLocation l1, PickupLocation l2){
        float[] result = new float[1];
        Location.distanceBetween(l1.latitude,l1.longitude,l2.latitude,l2.longitude, result);
        return result[0];
    }
}
