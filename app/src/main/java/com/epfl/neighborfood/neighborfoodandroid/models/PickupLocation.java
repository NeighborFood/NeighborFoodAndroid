package com.epfl.neighborfood.neighborfoodandroid.models;

import android.location.Location;

/**
 * PickupLocation class has the needed information about the location of the pickup
 */
public class PickupLocation extends Model {
    private double latitude;
    private double longitude;

    public PickupLocation() {
    }

    /**
     * Constructor for the pickup location
     *
     * @param latitude latitude of the location
     * @param longitude longitude of the location
     */
    public PickupLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter for the latitude of the location
     *
     * @return the latitude of the location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter for the latitude of the location
     *
     * @param latitude the latitude of the location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter for the longitude of the location
     *
     * @return the longitude of the location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter for the longitude of the location
     *
     * @param longitude the longitude of the location
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * calculates the distance between two locations
     *
     * @param l1 first location
     * @param l2 second location
     * @return the distance between the two location
     */
    public static float distanceBetweenLocations(PickupLocation l1, PickupLocation l2) {
        float[] result = new float[1];
        Location.distanceBetween(l1.latitude, l1.longitude, l2.latitude, l2.longitude, result);
        return result[0];
    }
}
