package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.AccountFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.ConversationsFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MainFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private NavigationBarView navbar;
    private Class<? extends Fragment> currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment = MainFragment.class;
        setCurrentFragment(currentFragment);
        navbar = findViewById(R.id.bottomNavigationView);
        navbar.setOnItemSelectedListener(this::switchFragment);
    }

    private boolean switchFragment(MenuItem it) {
        Class<? extends Fragment> selectedFragment;
        switch (it.getItemId()) {
            case R.id.navBarHome:
                selectedFragment = MainFragment.class;
                break;
            case R.id.navBarMessages:
                selectedFragment = ConversationsFragment.class;
                break;
            case R.id.navBarAccount:
                selectedFragment = AccountFragment.class;
                break;
            default:
                return false;
        }
        if (currentFragment != selectedFragment) {
            setCurrentFragment(selectedFragment);
            currentFragment = selectedFragment;
        }
        return true;
    }

    /**
     * Sets the current fragment to the given one
     *
     * @param fragment
     */
    private void setCurrentFragment(Class<? extends Fragment> fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment, null).commit();

    }
}
