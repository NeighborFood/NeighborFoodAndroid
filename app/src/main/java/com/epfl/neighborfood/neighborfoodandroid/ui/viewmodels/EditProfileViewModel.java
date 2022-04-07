package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;



public class EditProfileViewModel extends ViewModel {
    MutableLiveData<User> currentUser ;
    AuthRepository authRepo;

    public EditProfileViewModel (AuthRepository repository){
        authRepo = repository;
    }
    public LiveData<User> getCurrentUser(){
        if(currentUser == null){
            //fetch current user
            loadUser();
        }
        return currentUser;
    }

    public void saveUserData(){
    }

    private void loadUser(){
        currentUser = authRepo.getUserLiveData();

    }

}
