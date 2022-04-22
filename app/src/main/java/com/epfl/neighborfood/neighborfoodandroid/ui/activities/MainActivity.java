package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.Manifest;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.AccountFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.ConversationsFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MealListFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.VendorDashboardFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.epfl.neighborfood.MESSAGE";
    private NavigationBarView navbar;
    private TextView lat;
    private TextView lon;
    private Button toggleButton;
    private boolean isVendor;
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentFragment(MealListFragment.class);
        navbar = findViewById(R.id.bottomNavigationView);
        navbar.setOnItemSelectedListener(this::switchFragment);
        isVendor=false;
        toggleButton=findViewById(R.id.button2);
        toggleButton.setOnClickListener(this::toggleView);

        lat = findViewById(R.id.latitude);
        lon = findViewById(R.id.longitude);

        setupLocation();

    }


    private void toggleView(View view) {
        isVendor=!isVendor;
        switchFragment(navbar.getMenu().findItem(navbar.getSelectedItemId()));
    }

    private void setupLocation(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this , new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location){
                lon.setText(String.valueOf(location.getLongitude()));
                lat.setText(String.valueOf(location.getLatitude()));
            }
        });
    }

    private boolean switchFragment(MenuItem it){

        switch (it.getItemId()) {
            case R.id.navBarHome:
                if (isVendor){
                setCurrentFragment(VendorDashboardFragment.class);
                }
                else{
                    setCurrentFragment(MealListFragment.class);
            }
                return true;
            case R.id.navBarMessages:
                setCurrentFragment(ConversationsFragment.class);
                /*
                Intent intent = new Intent(this,ConversationsActivity.class);
                startActivity(intent);*/
                //Toast.makeText(this, "Not yet Implemented!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navBarAccount:
                setCurrentFragment(AccountFragment.class);
                return true;
            default:
                return false;
        }
    }
    private void setCurrentFragment(Class fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment,null)/*.addToBackStack(null)*/.commit();

    }

}
