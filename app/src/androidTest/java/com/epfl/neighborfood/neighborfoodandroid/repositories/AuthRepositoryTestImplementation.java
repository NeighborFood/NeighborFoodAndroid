package com.epfl.neighborfood.neighborfoodandroid.repositories;

import android.os.Handler;

import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.firebase.auth.AuthCredential;

public class AuthRepositoryTestImplementation extends AuthRepository{
    public AuthRepositoryTestImplementation(){
        super(new DummyAuthenticator());
    }
    @Override
    public void authenticateWithCredential(AuthCredential credential) {
        (new Handler()).postDelayed(()->{
            userLiveData.postValue(authenticator.getCurrentUser());
            loggedOutLiveData.postValue(false);
        }, 1000); //Fake Delay to simulate network request
    }

    public void setUser(User user){
        userLiveData.postValue(user);
        loggedOutLiveData.postValue(false);
    }
}
