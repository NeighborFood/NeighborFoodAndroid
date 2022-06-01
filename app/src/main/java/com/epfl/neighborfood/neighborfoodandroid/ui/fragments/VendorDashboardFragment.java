package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MealListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.adapters.VendorOrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentVendorDashboardBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorOrdersViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealListViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.VendorOrdersViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class VendorDashboardFragment extends Fragment {

    private FragmentVendorDashboardBinding binding;
    private FloatingActionButton button;
    private VendorOrdersViewModel viewModel;
    private VendorOrderListAdapter listAdapter;
    private ArrayList<Order> orderList = new ArrayList<Order>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentVendorDashboardBinding.inflate(getLayoutInflater());
        button = binding.getRoot().findViewById(R.id.addMealButton);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = null;
                                          switch (v.getId()) {
                                              case R.id.addMealButton:
                                                  intent = new Intent(getActivity(), PlaceMealActivity.class);
                                          }
                                          startActivity(intent);
                                      }
                                  }

        );
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        viewModel = new ViewModelProvider(this, new VendorOrdersViewModelFactory((NeighborFoodApplication) this.getActivity().getApplication())).get(VendorOrdersViewModel.class);
        listAdapter = new VendorOrderListAdapter(getContext(), orderList, viewModel);

        viewModel.getVendorOrders().addOnSuccessListener(orders -> {
            orderList.addAll(orders);
            System.out.println(orders.size());
            listAdapter.notifyDataSetChanged();
            RecyclerView recyclerView = binding.recyclerView;
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(listAdapter);
        });
    }

}