package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorProfileViewModel;

public class VendorProfileViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;

    public VendorProfileViewModelFactory(NeighborFoodApplication app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VendorProfileViewModel(app.getAppContainer().getUserRepo(),app.getAppContainer().getAuthRepo(),app.getAppContainer().getNotificationService());
    }
}
