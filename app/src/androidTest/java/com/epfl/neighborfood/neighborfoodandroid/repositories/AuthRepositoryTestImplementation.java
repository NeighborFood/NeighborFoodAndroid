package com.epfl.neighborfood.neighborfoodandroid.repositories;

import android.os.Handler;

import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class AuthRepositoryTestImplementation extends AuthRepository{
    @Override
    public void logInWithGoogleAccount(GoogleSignInAccount account) {
        (new Handler()).postDelayed(()->{
            userLiveData.postValue(authenticator.getCurrentUser());
            loggedInLiveData.postValue(true);
        }, 1000); //Fake Delay to simulate network request
    }

    /** Sets directly the currently authenticated user
     * @param user : the user to put as the logged in user
     */
    public void setUser(User user){
        userLiveData.postValue(user);
        loggedInLiveData.postValue(true);
    }
}
