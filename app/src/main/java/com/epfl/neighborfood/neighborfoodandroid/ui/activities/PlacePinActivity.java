package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import static com.epfl.neighborfood.neighborfoodandroid.services.notifications.LocationService.DEFAULT_LOCATION;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.LocationService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class PlacePinActivity extends AppCompatActivity
        implements OnMapReadyCallback, View.OnClickListener {

    private static final String TAG = PlacePinActivity.class.getSimpleName();
    private GoogleMap map;
    private LocationService locationService;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    private static final int DEFAULT_ZOOM = 15;


    private PickupLocation lastKnownLocation;

    double chosenLat, chosenLng;
    boolean meetingPointSet;

    Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationService = ((NeighborFoodApplication)getApplication()).getAppContainer().getLocationService();
        setContentView(R.layout.activity_place_pin);

        Button confirmButton = findViewById(R.id.button);
        confirmButton.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.PickupLocationToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        locationService.getPickupLocationLiveData().observe(this,newLocation->{
            lastKnownLocation = newLocation;
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();

        map.setOnMapClickListener(latLng -> {
            map.clear();
            map.addMarker(new MarkerOptions().position(latLng).title("Meeting Point"));
            chosenLat = latLng.latitude;
            chosenLng = latLng.longitude;
            meetingPointSet = true;
        });
    }

    private void getDeviceLocation() {

        try {
            if (locationService.getLocationPermissionGranted()) {
                Task<PickupLocation> locationResult = locationService.getDeviceLocation();
                locationResult.addOnCompleteListener( task -> {
                    if (task.isSuccessful()) {
                        lastKnownLocation = task.getResult();
                        if (lastKnownLocation != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lastKnownLocation.getLatitude(),
                                            lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");
                        Log.e(TAG, "Exception: %s", task.getException());
                        map.moveCamera(CameraUpdateFactory
                                .newLatLngZoom(new LatLng(DEFAULT_LOCATION.getLatitude(),DEFAULT_LOCATION.getLongitude()), DEFAULT_ZOOM));
                        map.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    private void getLocationPermission() {
        if(!locationService.getLocationPermissionGranted()){
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

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationService.getLocationPermissionGranted()) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
                getDeviceLocation();
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if ( meetingPointSet) {
                    final Intent data = new Intent();
                    data.putExtra("longitude",chosenLng);
                    data.putExtra("latitude",chosenLat);
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // tool bar Back Icon
        {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}