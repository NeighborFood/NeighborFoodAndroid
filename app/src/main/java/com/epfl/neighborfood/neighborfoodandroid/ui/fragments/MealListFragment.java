package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MealListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentMealListBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MealActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealListViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;
import com.epfl.neighborfood.neighborfoodandroid.util.Triplet;

import java.util.ArrayList;


public class MealListFragment extends Fragment {

    private FragmentMealListBinding binding;
    private MealListViewModel viewModel;
    private MealListAdapter listAdapter;
    private ArrayList<Pair<Order, Meal>> orderMealList = new ArrayList<Pair<Order, Meal>>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMealListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, new MealListViewModelFactory((NeighborFoodApplication) this.getActivity().getApplication())).get(MealListViewModel.class);
        listAdapter = new MealListAdapter(getActivity(), orderMealList);
        viewModel.getAllUnassignedMeals().addOnSuccessListener(mealList -> {
            listAdapter.clear();
            listAdapter.addAll(mealList);
        }).addOnFailureListener(System.out::println);
        binding.mealListView.setAdapter(listAdapter);
        binding.mealListView.setClickable(true);
        binding.mealListView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent i = new Intent(getActivity(), MealActivity.class);
            i.putExtra("orderId", orderMealList.get(position).getFirst().getOrderId());
            i.putExtra("mealId", orderMealList.get(position).getSecond().getMealId());
            startActivity(i);
        });
    }


}