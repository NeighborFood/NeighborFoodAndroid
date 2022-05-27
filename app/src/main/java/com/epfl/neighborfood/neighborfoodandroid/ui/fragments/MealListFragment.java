package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MealListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentMealListBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MealActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.MealListViewModelFactory;

import java.util.ArrayList;


public class MealListFragment extends Fragment {

    private FragmentMealListBinding binding;
    private MealListViewModel viewModel;
    private Button filterButton;
    private Location currentLocation;
    LocationManager locationManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMealListBinding.inflate(getLayoutInflater());
        filterButton = getActivity().findViewById(R.id.button3);
        filterButton.setOnClickListener(this::filterMeals);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                currentLocation = location;
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        int[] imageId = {R.drawable.poulet, R.drawable.couscous, R.drawable.paella,
                R.drawable.fondue, R.drawable.salade, R.drawable.soupe, R.drawable.tarte};
        viewModel = new ViewModelProvider(this, new MealListViewModelFactory((NeighborFoodApplication) this.getActivity().getApplication())).get(MealListViewModel.class);
        MealListAdapter listAdapter = new MealListAdapter(getActivity(), new ArrayList<Meal>());
        viewModel.getAllMeals().addOnSuccessListener(mealList->{
            listAdapter.clear();
            listAdapter.addAll(mealList);
        }).addOnFailureListener(System.out::println);
        binding.mealListView.setAdapter(listAdapter);
        binding.mealListView.setClickable(true);
        binding.mealListView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent i = new Intent(getActivity(), MealActivity.class);
            i.putExtra("name",((Meal)(listAdapter.getItem(position))).getName());
            i.putExtra("shortDes", ((Meal)(listAdapter.getItem(position))).getShortDescription());
            i.putExtra("longDes", ((Meal)(listAdapter.getItem(position))).getLongDescription());
            i.putExtra("imageid", ((Meal)(listAdapter.getItem(position))).getImageId());
            startActivity(i);
        });
    }

    private void filterMeals(View view) {
        MealListAdapter listAdapter = new MealListAdapter(getActivity(), new ArrayList<Meal>());
        viewModel.getMealsInRadius(currentLocation,0.02).addOnSuccessListener(mealList->{
            listAdapter.clear();
            listAdapter.addAll(mealList);
        }).addOnFailureListener(System.out::println);
        binding.mealListView.setAdapter(listAdapter);
    }



}