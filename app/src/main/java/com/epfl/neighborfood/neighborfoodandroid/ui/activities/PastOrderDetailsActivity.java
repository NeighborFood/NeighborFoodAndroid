package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityPastOrderDetailsBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.squareup.picasso.Picasso;

/**
 * This activity displays the details of a selected Past Order.
 */
public class PastOrderDetailsActivity extends AppCompatActivity {
    Order order;
    ActivityPastOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra(getResources().getString(R.string.order));
        binding = ActivityPastOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Picasso.get().load(order.getMeal().getImageUri()).into(binding.mealImage);
        binding.mealName.setText(order.getMeal().getName());
        binding.mealDesc.setText(order.getMeal().getShortDescription());
        binding.goVendorProfileId.setText(order.getMealVendor());
        binding.goVendorProfileId.setOnClickListener(e ->
        {
            Intent intentVendor = new Intent(PastOrderDetailsActivity.this, VendorProfileActivity.class);
            startActivity(intentVendor);

        });
    }

}
