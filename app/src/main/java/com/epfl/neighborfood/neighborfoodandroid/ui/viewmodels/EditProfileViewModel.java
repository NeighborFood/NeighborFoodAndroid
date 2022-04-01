package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthAppRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

@HiltViewModel
public class EditProfileViewModel extends ViewModel {
    MutableLiveData<User> currentUser ;
    AuthAppRepository authRepo;

    @Inject
    EditProfileViewModel (AuthAppRepository repository){
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
