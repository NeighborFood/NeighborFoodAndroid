package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;

public class MealListViewModelFactory implements ViewModelProvider.Factory {

    private NeighborFoodApplication app;

    public MealListViewModelFactory(NeighborFoodApplication app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MealListViewModel(app.getAppContainer().getMealRepo(), app.getAppContainer().getOrderRepo(),app.getAppContainer().getAuthRepo());
    }
}