package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrdersActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorOrdersViewModel;

public class VendorOrdersViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;

    public VendorOrdersViewModelFactory(NeighborFoodApplication app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VendorOrdersViewModel(app.getAppContainer().getAuthRepo(), app.getAppContainer().getOrderRepo(), app.getAppContainer().getMealRepo());
    }
}
