package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ChatRoomViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ConversationsViewModel;

public class ChatRoomViewModelFactory implements ViewModelProvider.Factory {
    private NeighborFoodApplication app;
    public ChatRoomViewModelFactory(NeighborFoodApplication application) {
        this.app = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return(T) new ChatRoomViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo(),app.getAppContainer().getConversationRepo());
    }
}
