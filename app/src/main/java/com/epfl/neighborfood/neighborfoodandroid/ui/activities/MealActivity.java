package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.AllergensAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityMealBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrderDetailsActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.BuyerOrderDetailsViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealListViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealViewModelFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity {

    ActivityMealBinding binding;
    MealViewModel viewModel;
    Meal meal;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new MealViewModelFactory((NeighborFoodApplication) this.getApplication())).get(MealViewModel.class);

        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null) {

            String mealId = intent.getStringExtra("mealId");
            String orderId = intent.getStringExtra("orderId");
            System.out.println(mealId+ "------"+ orderId);
            viewModel.getOrderById(orderId).addOnSuccessListener(orderFetched -> {
                order = orderFetched;
            });

            viewModel.getMealById(mealId).addOnSuccessListener(mealFetched -> {
                meal = mealFetched;
                Picasso.get().load(meal.getImageUri()).into(binding.mealImage);
                binding.mealName.setText(meal.getName());
                binding.mealDesc.setText(meal.getDescription());
                List<Allergen> allergens = meal.getAllergens();
                AllergensAdapter allergensAdapter = new AllergensAdapter(this, allergens);
                binding.allergensMeal.setAdapter(allergensAdapter);
            });

        }

        Button vendorButton = findViewById(R.id.go_vendor_profile_id);
        Button orderButton = findViewById(R.id.order_button);

        orderButton.setOnClickListener(v -> viewModel.assignOrder(order).addOnSuccessListener(t->{
                    Toast.makeText(this, "a new order has been made", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MealActivity.this, MainActivity.class);
                    startActivity(i);
                }
                ));

        // Listener to enable the click button to go to the vendor profile
        vendorButton.setOnClickListener(v -> {
            Intent vendorProfileIntent = new Intent(MealActivity.this, VendorProfileActivity.class);
            if(meal==null){
                return;
            }
            vendorProfileIntent.putExtra("userID", meal.getVendorID());
            startActivity(vendorProfileIntent);
        });

    }

}