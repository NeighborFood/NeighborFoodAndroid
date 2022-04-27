package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;


/**
 * The Profile Editing activity ViewModel
 * It serves as the entry point to the models and handles checking the user parameters
 */
public class EditProfileViewModel extends ViewModel {
    private MutableLiveData<User> currentUser;
    private AuthRepository authRepo;

    public EditProfileViewModel(AuthRepository repository) {
        authRepo = repository;
    }

    /**
     * Gets an observable object on the currently autheticated user
     *
     * @return the currently authenticated user LiveData
     */
    public LiveData<User> getCurrentUser() {
        if (currentUser == null) {
            //fetch current user
            currentUser = authRepo.getUserLiveData();
        }
        return currentUser;
    }


    /**
     * Updates the currently authenticated user with the user passed as parameter
     *
     * @param user : The user with updated fields
     */
    public void updateUser(User user) {
        if (user == null) {
            return;
        }
        //Verify user attributes
        if (!user.getId().equals(currentUser.getValue().getId())) {
            return;
        }
        authRepo.updateUser(user);
    }

}
