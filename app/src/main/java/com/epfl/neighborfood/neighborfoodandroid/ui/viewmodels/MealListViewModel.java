package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.List;

public class MealListViewModel extends ViewModel {
    private MealRepository mealRepository;
    public MealListViewModel(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }
    public Task<List<Pair<Order, Meal>>> getAllUnassignedMeals(){
        return mealRepository.getAllUnassignedMeals();
    }
}
