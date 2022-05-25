package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ConversationsViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.EditProfileViewModel;

public class ConversationsViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;
    public ConversationsViewModelFactory(NeighborFoodApplication app){
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return(T) new ConversationsViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo(),app.getAppContainer().getConversationRepo());
    }

}
