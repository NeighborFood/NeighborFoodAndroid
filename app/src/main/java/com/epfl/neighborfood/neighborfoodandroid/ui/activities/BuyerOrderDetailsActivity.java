package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityPastOrderDetailsBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrderDetailsActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;
import com.squareup.picasso.Picasso;

import java.util.Objects;


/**
 * This activity displays the details of a selected Past Order.
 */
public class BuyerOrderDetailsActivity extends AppCompatActivity {
    private Order order;
    private Meal meal;
    private User vendor;
    private OrderStatus status;
    private ActivityPastOrderDetailsBinding binding;
    private BuyerOrderDetailsActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) this.getApplication())).get(BuyerOrderDetailsActivityViewModel.class);


        Intent intent = getIntent();
        String orderId = (String) intent.getSerializableExtra("orderId");
        viewModel.getOrderById(orderId).addOnSuccessListener(orderFetched -> {
            order = orderFetched;
            if(order.getOrderStatus() == OrderStatus.finished) {
                System.out.println("helloo");
                binding.ConfirmationButton.setVisibility(View.GONE);
            }
            else {
                binding.ConfirmationButton.setOnClickListener(e -> viewModel.confirmOrder(order)
                        .addOnSuccessListener(t -> {
                            Toast.makeText(this, "the order has been delivered", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(BuyerOrderDetailsActivity.this, BuyerOrdersActivity.class);
                            startActivity(i);
                        }));
            }
            fetchOrderDetails();
        });

        binding = ActivityPastOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.buyerPastOrdersToolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    private void fetchOrderDetails() {
        viewModel.getMealById(order.getMealId()).addOnSuccessListener(meal -> {
            this.meal = meal;
            updateMealDetails();
        });
        viewModel.getUserById(order.getVendorId()).addOnSuccessListener(user -> {
            this.vendor = user;
            updateVendorDetails();
        });
    }

    private void updateVendorDetails() {
        ImageView vendorImage = findViewById(R.id.go_vendor_profile_id);
        binding.vendorName.setText(vendor.getUsername());
        Picasso.get().load(vendor.getProfilePictureURI()).into(vendorImage);
        ImageView mapButton = findViewById(R.id.pickupLocation);

        mapButton.setOnClickListener(v -> {
            Intent mapIntent = new Intent(BuyerOrderDetailsActivity.this, MapActivity.class);
            if(meal==null){
                return;
            }
            mapIntent.putExtra("latitude","46.5191");
            mapIntent.putExtra("longitude", "6.5668");
            startActivity(mapIntent);
        });
        binding.goVendorProfileId.setOnClickListener(e ->
        {
            Intent intentVendor = new Intent(BuyerOrderDetailsActivity.this, VendorProfileActivity.class);
            intentVendor.putExtra("userID", vendor.getId());
            startActivity(intentVendor);

        });
    }

    private void updateMealDetails() {
        Picasso.get().load(meal.getImageUri()).into(binding.mealImage);
        binding.mealName.setText(meal.getName());
        binding.mealDesc.setText(meal.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // tool bar Back Icon
        {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
