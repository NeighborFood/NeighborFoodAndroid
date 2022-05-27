package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.BuyerOrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.adapters.VendorOrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrdersActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.BuyerOrdersViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.util.Triplet;

import java.util.ArrayList;

public class BuyerOrdersActivity extends AppCompatActivity {
    private ListView listView;
    private BuyerOrdersActivityViewModel viewModel;
    private BuyerOrderListAdapter buyerOrderListAdapter;
    private ArrayList<Triplet<Order, Meal, User>> buyerOrderList = new ArrayList<Triplet<Order, Meal, User>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orders);

        viewModel = new ViewModelProvider(this, new BuyerOrdersViewModelFactory((NeighborFoodApplication) this.getApplication())).get(BuyerOrdersActivityViewModel.class);

        buyerOrderListAdapter = new BuyerOrderListAdapter(this, buyerOrderList);

        viewModel.getUserOrders().addOnSuccessListener(orderList -> {
            buyerOrderListAdapter.clear();

            for (Order order :
                    orderList) {
                System.out.println(order.getBuyerId());
                viewModel.getMealById(order.getMealId()).addOnSuccessListener(meal ->
                        viewModel.getUserById(order.getVendorId()).addOnSuccessListener(vendor ->
                                buyerOrderListAdapter.add(Triplet.of(order, meal, vendor))));
            }
        });


        listView = (ListView) findViewById(R.id.order_list_view);
        listView.setAdapter(buyerOrderListAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(BuyerOrdersActivity.this, BuyerOrderDetailsActivity.class);
            intent.putExtra("orderId", buyerOrderList.get(position).getFirst().getOrderId());
            startActivity(intent);
        });
    }

}
