package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.EditProfileViewModel;

public class EditProfileViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;
    public EditProfileViewModelFactory(NeighborFoodApplication app){
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return(T) new EditProfileViewModel(app.getAppContainer().getAuthRepo());
    }
}
