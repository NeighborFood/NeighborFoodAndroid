package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.BuyerOrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrdersActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.util.Triplet;

import java.util.ArrayList;
import java.util.Objects;

public class BuyerOrdersActivity extends AppCompatActivity {
    private ListView listView;
    private BuyerOrdersActivityViewModel viewModel;
    private BuyerOrderListAdapter buyerOrderListAdapter;
    private final ArrayList<Triplet<Order, Meal, User>> buyerOrderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orders);

        Toolbar toolbar = findViewById(R.id.buyerOrdersToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) this.getApplication())).get(BuyerOrdersActivityViewModel.class);

        buyerOrderListAdapter = new BuyerOrderListAdapter(this, buyerOrderList);

        viewModel.getUserOrders().addOnSuccessListener(orderList -> {
            buyerOrderListAdapter.clear();

            for (Order order :
                    orderList) {
                viewModel.getMealById(order.getMealId()).addOnSuccessListener(meal ->
                        viewModel.getUserById(order.getVendorId()).addOnSuccessListener(vendor ->
                                buyerOrderListAdapter.add(Triplet.of(order, meal, vendor))));
            }
        });


        listView = findViewById(R.id.order_list_view);
        listView.setAdapter(buyerOrderListAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(BuyerOrdersActivity.this, BuyerOrderDetailsActivity.class);
            intent.putExtra("orderId", buyerOrderList.get(position).getFirst().getOrderId());
            startActivity(intent);
        });
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
