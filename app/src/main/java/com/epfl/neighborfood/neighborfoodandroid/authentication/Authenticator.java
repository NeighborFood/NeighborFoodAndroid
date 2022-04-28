package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

/**
 * Authentication interface
 * Represents the model for authentication
 */
public interface Authenticator {

    /**
     * Returns the user currently signed in
     *
     * @return user, null if no user is logged in
     */
    User getCurrentUser();

    /**
     * Logs out the current User
     */
    void logOut();

    /**
     * Logs in to the app with a google sign in account
     *
     * @param googleAccount the google account to sign in with
     * @return the Authentication task
     */
    Task<Void> logInWithGoogleAccount(GoogleSignInAccount googleAccount);


}