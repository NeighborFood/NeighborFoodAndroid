package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

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

        ArrayList<Allergen> allergens = new ArrayList<>();
        allergens.add(Allergen.EGGS);
        allergens.add(Allergen.HONEY);
        allergens.add(Allergen.GLUTEN);
        allergens.add(Allergen.LOBSTER);
        AllergensAdapter allergensAdapter = new AllergensAdapter(this, allergens);
        binding.allergensMeal.setAdapter(allergensAdapter);


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