package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

/**
 * ViewModel for the SignUp Activity
 * Serves as the entry point for the view to the authentication repository
 */
public class SignUpViewModel extends ViewModel {
    private final AuthRepository authRepo;
    private LiveData<User> user;

    /**
     * Constructor for the SignupViewModel
     *
     * @param authRepo the Authentication repository of the app
     */
    public SignUpViewModel(AuthRepository authRepo) {
        this.authRepo = authRepo;
    }

    /**
     * Returns an observable object on the currently authenticated user
     *
     * @return the current
     */
    public LiveData<User> getCurrentUser() {
        if (user == null) {
            user = authRepo.getUserLiveData();
        }
        return user;
    }

    /**
     * Requests to Sign out the currently authenticated user
     */
    public void signOut() {
        authRepo.logOut();
    }

    /**
     * Handles the result of the google login activity
     *
     * @param resultCode the result code of the intent
     * @param data       the data received from login activity
     */
    public Task<Void> handleGoogleLoginResponse(int resultCode, Intent data) {
        Task<GoogleSignInAccount> getAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
        getAccountTask.addOnSuccessListener(authRepo::logInWithGoogleAccount);
        return getAccountTask.continueWith(task->null);
    }
}
