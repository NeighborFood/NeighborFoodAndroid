package com.epfl.neighborfood.neighborfoodandroid.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.epfl.neighborfood.neighborfoodandroid.authentication.Authenticator;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.authentication.FirebaseAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.models.UserFirebaseImpl;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

public abstract class AuthRepository {

    protected Authenticator authenticator;
    protected MutableLiveData<User> userLiveData;
    protected MutableLiveData<Boolean> loggedOutLiveData;

    public AuthRepository(Authenticator authenticator) {
        this.userLiveData = new MutableLiveData<>();
        this.loggedOutLiveData = new MutableLiveData<>();
        this.authenticator = authenticator;
        if (authenticator.getCurrentUser() != null) {
            userLiveData.postValue(authenticator.getCurrentUser());
            loggedOutLiveData.postValue(false);
        }
    }
    public void updateUser(User user){
        userLiveData.postValue(user);
        loggedOutLiveData.postValue(user==null);
        //TODO: Update user attributes in db

    }
    public void logOut(){
        authenticator.logOut();
        loggedOutLiveData.postValue(true);
        userLiveData.postValue(null);
    }
    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }

    public abstract void authenticateWithCredential(AuthCredential credential);

}
