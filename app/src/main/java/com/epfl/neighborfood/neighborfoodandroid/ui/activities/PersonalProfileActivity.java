package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class PersonalProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
    }
    public void goToEditProfile(View view){
        Intent intent = new Intent(this, ProfileEditingActivity.class);
        startActivity(intent);
    }

}