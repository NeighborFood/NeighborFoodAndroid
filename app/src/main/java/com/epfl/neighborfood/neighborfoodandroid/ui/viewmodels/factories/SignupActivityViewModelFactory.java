package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.SignUpViewModel;

public class SignupActivityViewModelFactory implements ViewModelProvider.Factory{
    private NeighborFoodApplication app;
    public SignupActivityViewModelFactory(NeighborFoodApplication app){
        this.app = app;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignUpViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo());
    }
}
