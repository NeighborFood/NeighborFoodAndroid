package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.AllergensAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityMealBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrderDetailsActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.BuyerOrderDetailsViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealViewModelFactory;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    ActivityMealBinding binding;
    MealViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this, new MealViewModelFactory((NeighborFoodApplication) this.getApplication())).get(MealViewModel.class);


        Button vendorButton = (Button) findViewById(R.id.go_vendor_profile_id);
        Button orderButton = (Button) findViewById(R.id.order_button);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

            binding.mealImage.setImageResource(R.drawable.paella);
            binding.mealName.setText(name);
            binding.mealDesc.setText(longDes);

        }

    }

}