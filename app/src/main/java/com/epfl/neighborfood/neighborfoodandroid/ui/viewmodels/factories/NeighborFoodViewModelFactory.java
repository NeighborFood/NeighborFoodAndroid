package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrderDetailsActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.BuyerOrdersActivityViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ChatRoomViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ConversationsViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.EditProfileViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.PlaceMealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.SignUpViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorOrdersViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorProfileViewModel;

public class NeighborFoodViewModelFactory implements ViewModelProvider.Factory{
    private NeighborFoodApplication app;

    public NeighborFoodViewModelFactory(NeighborFoodApplication app) {
        this.app = app;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == BuyerOrderDetailsActivityViewModel.class){
            return (T) new BuyerOrderDetailsActivityViewModel(app.getAppContainer().getAuthRepo(), app.getAppContainer().getOrderRepo(),app.getAppContainer().getMealRepo(), app.getAppContainer().getUserRepo());
        }
        if(modelClass == BuyerOrdersActivityViewModel.class){
            return (T) new BuyerOrdersActivityViewModel(app.getAppContainer().getAuthRepo(), app.getAppContainer().getOrderRepo(),app.getAppContainer().getMealRepo(), app.getAppContainer().getUserRepo());
        }
        if(modelClass == ChatRoomViewModel.class){
            return(T) new ChatRoomViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo(),app.getAppContainer().getConversationRepo());
        }
        if(modelClass == ConversationsViewModel.class){
            return(T) new ConversationsViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo(),app.getAppContainer().getConversationRepo());
        }
        if(modelClass == EditProfileViewModel.class){
            return(T) new EditProfileViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo());
        }
        if(modelClass == MealListViewModel.class){
            return (T) new MealListViewModel(app.getAppContainer().getMealRepo(), app.getAppContainer().getOrderRepo(),app.getAppContainer().getAuthRepo());
        }
        if(modelClass == MealViewModel.class){
            return (T) new MealViewModel(app.getAppContainer().getOrderRepo(), app.getAppContainer().getMealRepo(),
                    app.getAppContainer().getUserRepo(), app.getAppContainer().getAuthRepo());
        }
        if(modelClass == PlaceMealViewModel.class){
            return (T) new PlaceMealViewModel(app.getAppContainer().getMealRepo(),app.getAppContainer().getAuthRepo(),app.getAppContainer().getOrderRepo());
        }
        if(modelClass == SignUpViewModel.class){
            return (T) new SignUpViewModel(app.getAppContainer().getAuthRepo(),app.getAppContainer().getUserRepo());
        }
        if(modelClass == VendorProfileViewModel.class){
            return (T) new VendorProfileViewModel(app.getAppContainer().getUserRepo(),app.getAppContainer().getAuthRepo(),app.getAppContainer().getNotificationService());
        }
        if(modelClass == VendorOrdersViewModel.class){
            return (T) new VendorOrdersViewModel(app.getAppContainer().getAuthRepo(), app.getAppContainer().getOrderRepo(), app.getAppContainer().getMealRepo());
        }

        throw new IllegalStateException("View model not yet implemented");
    }
}
