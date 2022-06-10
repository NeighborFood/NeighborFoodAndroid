package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/**
 * ViewModel for the SignUp Activity
 * Serves as the entry point for the view to the authentication repository
 */
public class SignUpViewModel extends ViewModel {
    private final AuthRepository authRepo;
    private final UserRepository userRepo;

    /**
     * Constructor for the SignupViewModel
     *
     * @param authRepo the Authentication repository of the app
     */
    public SignUpViewModel(AuthRepository authRepo, UserRepository userRepo) {

        this.authRepo = authRepo;
        this.userRepo = userRepo;
    }

    /**
     * Returns the currently authenticator user
     *
     * @return the current
     */
    public AuthenticatorUser getCurrentAuthUser() {
        return authRepo.getAuthUser();
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
    public Task<Boolean> handleGoogleLoginResponse(int resultCode, Intent data) {
        Task<GoogleSignInAccount> getAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
        return getAccountTask.continueWithTask(t -> authRepo.logInWithGoogleAccount(t.getResult())).continueWithTask(
                t -> {
                    if (t.isSuccessful()) { // if we could get the authenticated user, see if we can find him in the db
                        return userRepo.getUserById(t.getResult().getId());
                    }
                    return Tasks.forException(new RuntimeException("Could not login with google account"));
                }
        ).continueWithTask(t -> {
            if (t.isSuccessful() && t.getResult() != null) { // if the user already exists, we finish the task
                return Tasks.forResult(false);
            } else { // otherwise we register him to the database

                return registerUser(authRepo.getAuthUser());
            }
        });
    }


    /**
     * Registers a new user on the database, from an authenticated user
     *
     * @param authUser : the auth user that we will base our app user from
     * @return the void that will complete after user registers
     */
    private Task<Boolean> registerUser(AuthenticatorUser authUser) {

        //String encodedImage = ImageUtil.imageToString(ImageUtil.loadImageFromUri(authUser.getPpUri()));
        User newUser = new User(authUser.getId(), authUser.getEmail(), authUser.getFirstName(), authUser.getLastName(), authUser.getPpUri());
        return userRepo.updateUser(newUser).continueWith(t -> true);
    }
}
