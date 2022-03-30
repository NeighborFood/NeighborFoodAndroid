package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityMealBinding;

public class MealActivity extends AppCompatActivity {

    ActivityMealBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null) {

            String name = intent.getStringExtra("name");
            String shortDes = intent.getStringExtra("shortDes");
            String longDes = intent.getStringExtra("longDes");
            int imageId = intent.getIntExtra("imageid", R.drawable.paella);

            binding.mealImage.setImageResource(imageId);
            binding.mealName.setText(name);
            binding.mealDesc.setText(longDes);

        }

    }

}