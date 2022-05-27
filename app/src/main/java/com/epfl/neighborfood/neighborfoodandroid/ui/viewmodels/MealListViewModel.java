package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import android.location.Location;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MealListViewModel extends ViewModel {
    private MealRepository mealRepository;
    public MealListViewModel(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }
    public Task<List<Meal>> getAllMeals(){
        return mealRepository.getAllMeals();
    }

    public Task<List<Meal>> getMealsInRadius(Location loc, double radius){
        return mealRepository.getMealsInRadius(loc, radius);
    }
}
