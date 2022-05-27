package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealViewModel;

public class MealViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;

    public MealViewModelFactory(NeighborFoodApplication app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MealViewModel(app.getAppContainer().getOrderRepo(), app.getAppContainer().getMealRepo(),
                app.getAppContainer().getUserRepo(), app.getAppContainer().getAuthRepo());
    }
}
