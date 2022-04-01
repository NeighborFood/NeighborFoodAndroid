package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.AccountFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.ConversationsFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MealListFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.epfl.neighborfood.MESSAGE";
    private NavigationBarView navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentFragment(MealListFragment.class);
        navbar =findViewById(R.id.bottomNavigationView);
        navbar.setOnItemSelectedListener(this::switchFragment);
    }
    private boolean switchFragment(MenuItem it){

        switch (it.getItemId()) {
            case R.id.navBarHome:
                setCurrentFragment(MealListFragment.class);
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
            case R.id.navBarOrderHistory:
                Intent orderIntent = new Intent(this, OrderHistoryActivity.class);
                startActivity(orderIntent);
            default:
                return false;
        }
    }
    private void setCurrentFragment(Class fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment,null)/*.addToBackStack(null)*/.commit();

    }
}
