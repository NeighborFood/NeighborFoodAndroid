package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.services.location.LocationService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback{

    private GoogleMap map;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final int DEFAULT_ZOOM = 15;

    private LocationService locationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationService = ((NeighborFoodApplication)getApplication()).getAppContainer().getLocationService();

        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;
        // Add meal marker
        // and move the map's camera to the same location.
        LatLng pickupLocation = new LatLng(getLatFromIntent(), getLonFromIntent());
        googleMap.addMarker(new MarkerOptions()
                .position(pickupLocation)
                .title("Pickup Location"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pickupLocation, DEFAULT_ZOOM));
        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
    }


    private void getDeviceLocation() {
            if (locationService.getLocationPermissionGranted()) {
                Task<PickupLocation> locationResult = locationService.getDeviceLocation();
                locationResult.addOnCompleteListener(this, task -> {
                    if (!task.isSuccessful()) {
                        map.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                });
            }

    }

    private void getLocationPermission() {
        if (!locationService.getLocationPermissionGranted()) {
            locationService.requestLocationPermission(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            updateLocationUI();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @SuppressLint("MissingPermission")
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
            if (locationService.getLocationPermissionGranted()) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
            }
    }


    private Double getLonFromIntent(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            return extras.getDouble("longitude");
        }
        return null;
    }

    private Double getLatFromIntent(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            return extras.getDouble("latitude");
        }
        return null;
    }
}
