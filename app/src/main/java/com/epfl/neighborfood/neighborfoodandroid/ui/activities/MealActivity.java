package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.AllergensAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityMealBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    ActivityMealBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Button vendorButton = (Button)findViewById(R.id.go_vendor_profile_id);


        // Listener to enable the click button to go to the vendor profile
        vendorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MealActivity.this, VendorProfileActivity.class));
            }
        });

        Intent intent = this.getIntent();

        if (intent != null) {

            String name = intent.getStringExtra("name");
            String shortDes = intent.getStringExtra("shortDes");
            String longDes = intent.getStringExtra("longDes");
            int imageId = intent.getIntExtra("imageid", R.drawable.paella);

            // TODO adapt to get the correct allergens when the database will be setup
            ArrayList<Allergen> allergens = new ArrayList<>();
            allergens.add(Allergen.CELERY);
            allergens.add(Allergen.CHEESE);
            allergens.add(Allergen.GLUTEN);
            AllergensAdapter allergensAdapter = new AllergensAdapter(this, allergens);
            binding.allergensMeal.setAdapter(allergensAdapter);

            binding.mealImage.setImageResource(imageId);
            binding.mealName.setText(name);
            binding.mealDesc.setText(longDes);

        }

    }

}