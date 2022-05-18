package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.util.ImageUtil;
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
    public Task<Void> placeMeal(Meal meal,String imagePath){
        meal.setVendorID(authRepository.getAuthUser().getId());
        return ImageUtil.uploadImage(imagePath).continueWithTask(imageUploadTask->{
            meal.setImageUri(imageUploadTask.getResult());
            return mealRepository.postMeal(meal);
        }).continueWith(task -> null);
    }
}
