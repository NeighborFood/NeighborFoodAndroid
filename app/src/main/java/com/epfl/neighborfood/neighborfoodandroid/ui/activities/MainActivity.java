package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.AccountFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MealListFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.VendorDashboardFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.epfl.neighborfood.MESSAGE";
    private NavigationBarView navbar;
    private Button toggleButton;
    private boolean isVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentFragment(MealListFragment.class);
        navbar = findViewById(R.id.bottomNavigationView);
        navbar.setOnItemSelectedListener(this::switchFragment);
        isVendor = false;
        toggleButton = findViewById(R.id.button2);
        toggleButton.setOnClickListener(this::toggleView);
    }

    private void toggleView(View view) {
        isVendor = !isVendor;
        switchFragment(navbar.getMenu().findItem(navbar.getSelectedItemId()));
    }

    private boolean switchFragment(MenuItem it) {

        switch (it.getItemId()) {
            case R.id.navBarHome:
                if (isVendor) {
                    setCurrentFragment(VendorDashboardFragment.class);
                } else {
                    setCurrentFragment(MealListFragment.class);
                }
                return true;
            case R.id.navBarMessages:
                return true;
            case R.id.navBarAccount:
                setCurrentFragment(AccountFragment.class);
                return true;
            default:
                return false;
        }
    }

    /**
     * Sets the current fragment to the given one
     *
     * @param fragment
     */
    private void setCurrentFragment(Class fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment, null).commit();

    }


}
