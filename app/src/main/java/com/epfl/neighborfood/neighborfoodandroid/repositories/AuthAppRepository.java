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

public class AuthAppRepository {

    private FirebaseAuthenticator authenticator;
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;

    public AuthAppRepository() {
        this.authenticator = new FirebaseAuthenticator();
        this.userLiveData = new MutableLiveData<>();
        this.loggedOutLiveData = new MutableLiveData<>();

        if (authenticator.getCurrentUser() != null) {
            userLiveData.postValue(authenticator.getCurrentUser());
            loggedOutLiveData.postValue(false);
        }
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

    public void authenticateWithCredential(AuthCredential credential){
        authenticator.autheticateWithCredential(credential).addOnCompleteListener(task->{
            if(task.isSuccessful()){
                userLiveData.postValue(authenticator.getCurrentUser());
                loggedOutLiveData.postValue(false);
            }
        });
    }

}
