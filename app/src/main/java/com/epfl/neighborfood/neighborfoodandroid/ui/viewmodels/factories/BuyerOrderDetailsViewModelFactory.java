package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrdersActivityViewModel;

public class BuyerOrderDetailsViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;

    public BuyerOrderDetailsViewModelFactory(NeighborFoodApplication app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BuyerOrdersActivityViewModel(app.getAppContainer().getAuthRepo(), app.getAppContainer().getOrderRepo(),app.getAppContainer().getMealRepo(), app.getAppContainer().getUserRepo());
    }
}
