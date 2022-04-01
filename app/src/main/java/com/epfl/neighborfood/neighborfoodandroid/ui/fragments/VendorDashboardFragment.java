package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MealListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.adapters.OrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentVendorDashboardBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;

import java.sql.Array;
import java.util.ArrayList;

public class VendorDashboardFragment extends Fragment {

    private FragmentVendorDashboardBinding binding;
    private ArrayList<String> nm;
    private ArrayList<Integer> id;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentVendorDashboardBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        id = new ArrayList<Integer>();
        id.add(R.drawable.poulet);
        id.add(R.drawable.couscous);
        id.add(R.drawable.paella);

        nm = new ArrayList<String>();
        nm.add("Poulet au miel");
        nm.add("Couscous aux légumes");
        nm.add("Paella aux crevettes");



        String[] mealsShortDes = {"Un délicieux poulet au miel",
                "Un couscous comme à la maison",
                "Une paella traditionnelle"};

        String[] mealsLongDes = {"Vous ne pourrez pas résister à ce savoureux poulet",
                "Ce couscous me fait penser à celui que me faisait mon grand-père",
                "Recette de paella directement d'Italie !"};

        ArrayList<Meal> mealArrayList = new ArrayList<>();

        for (int i = 0; i < mealsLongDes.length; i++) {
            Meal meal = new Meal(nm.get(i), mealsShortDes[i], mealsLongDes[i], id.get(i));
            mealArrayList.add(meal);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        OrderListAdapter adapter = new OrderListAdapter(getContext(), nm, id);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
    private void initRecyclerView(){

    }

    public void onClick(View v){
        Intent intent = null;
        switch(v.getId()){
            case R.id.addMealButton:
                intent = new Intent(getActivity(), PlaceMealActivity.class);
        }
        startActivity(intent);
    }

}