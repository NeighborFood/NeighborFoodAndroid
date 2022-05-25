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
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrderDetailsActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.BuyerOrderDetailsViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealViewModelFactory;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    ActivityMealBinding binding;
    MealViewModel viewModel;
    Meal meal;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null) {

            String mealId = intent.getStringExtra("mealId");
            String orderId = intent.getStringExtra("orderId");

            viewModel.getOrderById(orderId).addOnSuccessListener(orderFetched -> {
                order = orderFetched;
            });

            viewModel.getMealById(mealId).addOnSuccessListener(mealFetched -> {
                meal = mealFetched;
            });
            // TODO adapt to get the correct allergens when the database will be setup
            ArrayList<Allergen> allergens = new ArrayList<>();
            allergens.add(Allergen.CELERY);
            allergens.add(Allergen.CHEESE);
            allergens.add(Allergen.GLUTEN);
            AllergensAdapter allergensAdapter = new AllergensAdapter(this, allergens);
            binding.allergensMeal.setAdapter(allergensAdapter);

            binding.mealImage.setImageResource(meal.getImageId());
            binding.mealName.setText(meal.getName());
            binding.mealDesc.setText(meal.getLongDescription());

        }
        viewModel = new ViewModelProvider(this, new MealViewModelFactory((NeighborFoodApplication) this.getApplication())).get(MealViewModel.class);


        Button vendorButton = (Button) findViewById(R.id.go_vendor_profile_id);
        Button orderButton = (Button) findViewById(R.id.order_button);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.assignOrder(order);
            }
        });

        // Listener to enable the click button to go to the vendor profile
        vendorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MealActivity.this, VendorProfileActivity.class));
            }
        });

    }

}