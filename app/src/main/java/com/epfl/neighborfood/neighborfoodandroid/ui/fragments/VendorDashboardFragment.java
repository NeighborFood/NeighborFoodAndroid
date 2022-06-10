package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.VendorOrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentVendorDashboardBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorOrdersViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VendorDashboardFragment extends Fragment {

    private FragmentVendorDashboardBinding binding;
    private FloatingActionButton button;
    private VendorOrdersViewModel viewModel;
    private VendorOrderListAdapter unassignedListAdapter;
    private VendorOrderListAdapter waitingListAdapter;
    private VendorOrderListAdapter deliveredListAdapter;

    private final ArrayList<Order> unassignedOrderList = new ArrayList<>();
    private final ArrayList<Order> waitingOrderList = new ArrayList<>();
    private final ArrayList<Order> deliveredOrderList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentVendorDashboardBinding.inflate(getLayoutInflater());
        button = binding.getRoot().findViewById(R.id.addMealButton);
        button.setOnClickListener(v -> {
            if (v.getId() == R.id.addMealButton) {
                Intent intent = new Intent(getActivity(), PlaceMealActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        viewModel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) this.getActivity().getApplication())).get(VendorOrdersViewModel.class);
        unassignedListAdapter = new VendorOrderListAdapter(getContext(), unassignedOrderList, viewModel);
        RecyclerView unassignedRecyclerView = binding.unassignedRecyclerView;
        unassignedRecyclerView.setAdapter(unassignedListAdapter);
        unassignedRecyclerView.setLayoutManager(layoutManager);
        viewModel.getUnassignedVendorOrders().addOnSuccessListener(orders -> {
            unassignedOrderList.addAll(orders);
            unassignedListAdapter.notifyDataSetChanged();
        });
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        waitingListAdapter = new VendorOrderListAdapter(getContext(), waitingOrderList, viewModel);
        RecyclerView waitingRecyclerView = binding.waitingRecyclerView;
        waitingRecyclerView.setAdapter(waitingListAdapter);
        waitingRecyclerView.setLayoutManager(layoutManager1);
        viewModel.getWaitingVendorOrders().addOnSuccessListener(orders -> {
            waitingOrderList.addAll(orders);
            waitingListAdapter.notifyDataSetChanged();
        });
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        deliveredListAdapter = new VendorOrderListAdapter(getContext(), deliveredOrderList, viewModel);
        RecyclerView deliveredRecyclerView = binding.deliveredRecyclerView;
        deliveredRecyclerView.setAdapter(deliveredListAdapter);
        deliveredRecyclerView.setLayoutManager(layoutManager2);
        viewModel.getFinishedVendorOrders().addOnSuccessListener(orders -> {
            deliveredOrderList.addAll(orders);
            deliveredListAdapter.notifyDataSetChanged();
        });
    }

}