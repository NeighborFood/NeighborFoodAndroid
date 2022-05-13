package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.google.android.gms.tasks.Task;

public class PlaceMealViewModel extends ViewModel {
    private final MealRepository mealRepository;
    private final AuthRepository authRepository;

    public PlaceMealViewModel(MealRepository mealRepository, AuthRepository authRepository) {
        this.mealRepository = mealRepository;
        this.authRepository = authRepository;
    }

    /** Requests to place a meal on the database
     * @param meal : the meal to post
     * @return the completable task
     */
    public Task<Void> placeMeal(Meal meal){
        meal.setVendorID(authRepository.getAuthUser().getId());
        System.out.println(meal.getVendorID());
        return mealRepository.postMeal(meal).continueWith(task -> null);
    }
}
