package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MealListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentVendorDashboardBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;

import java.util.ArrayList;

public class VendorDashboardFragment extends Fragment {

    private FragmentVendorDashboardBinding binding;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentVendorDashboardBinding.inflate(getLayoutInflater());
        return binding.getRoot();}
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        int[] imageId = {R.drawable.poulet, R.drawable.couscous, R.drawable.paella};

        String[] mealsName = {"Poulet au miel",
                "Couscous aux légumes",
                "Paella aux crevettes"};

        String[] mealsShortDes = {"Un délicieux poulet au miel",
                "Un couscous comme à la maison",
                "Une paella traditionnelle"};

        String[] mealsLongDes = {"Vous ne pourrez pas résister à ce savoureux poulet",
                "Ce couscous me fait penser à celui que me faisait mon grand-père",
                "Recette de paella directement d'Italie !"};

        ArrayList<Meal> mealArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            Meal meal = new Meal(mealsName[i], mealsShortDes[i], mealsLongDes[i], imageId[i]);
            mealArrayList.add(meal);
        }

        MealListAdapter listAdapter = new MealListAdapter(getActivity(), mealArrayList);

        //fill with placeholder meals till we define order model
        /*
        binding.curOrderView.setAdapter(listAdapter);
        binding.curOrderView.setClickable(true);
        binding.curOrderView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //to do
            }
        }); */





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