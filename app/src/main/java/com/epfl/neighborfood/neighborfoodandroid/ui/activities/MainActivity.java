package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.epfl.neighborfood.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.fragmentContainerView, MealListFragment.class,null);
        transaction.commit();
        findViewById(R.id.HomeNavbarButton).setOnClickListener((View v)->{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,MealListFragment.class,null).addToBackStack(null).commit();
        });
        findViewById(R.id.AccountNavbarButton).setOnClickListener((View v)->{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,AccountFragment.class,null).addToBackStack(null).commit();
        });
    }

}
