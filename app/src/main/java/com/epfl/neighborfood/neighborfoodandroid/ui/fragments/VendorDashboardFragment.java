package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.VendorOrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentVendorDashboardBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class VendorDashboardFragment extends Fragment {

    private FragmentVendorDashboardBinding binding;
    private FloatingActionButton button;
    private ArrayList<String> nm;
    private ArrayList<Integer> id;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentVendorDashboardBinding.inflate(getLayoutInflater());
        button = binding.getRoot().findViewById(R.id.addMealButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = null;
                switch(v.getId()){
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
            Meal meal = new Meal(nm.get(i), mealsShortDes[i], mealsLongDes[i], "",new ArrayList<>(), 0, new Date());
            mealArrayList.add(meal);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        VendorOrderListAdapter adapter = new VendorOrderListAdapter(getContext(), nm, id);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
    private void initRecyclerView(){

    }



}