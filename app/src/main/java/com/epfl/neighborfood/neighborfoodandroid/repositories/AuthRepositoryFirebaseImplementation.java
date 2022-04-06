package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.authentication.FirebaseAuthenticator;
import com.google.firebase.auth.AuthCredential;

public class AuthRepositoryFirebaseImplementation extends  AuthRepository{
    public AuthRepositoryFirebaseImplementation(){
        super(new FirebaseAuthenticator());
        authenticator = new FirebaseAuthenticator();
    }
    public void authenticateWithCredential(AuthCredential credential){
        ((FirebaseAuthenticator)authenticator).autheticateWithCredential(credential).addOnCompleteListener(task->{
            if(task.isSuccessful()){
                userLiveData.postValue(authenticator.getCurrentUser());
                loggedOutLiveData.postValue(false);
            }
        });
    }
}
