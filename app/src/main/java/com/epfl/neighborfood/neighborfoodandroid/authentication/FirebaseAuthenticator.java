package com.epfl.neighborfood.neighborfoodandroid.authentication;

import androidx.annotation.NonNull;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.models.UserFirebaseImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthenticator implements Authenticator {

    private static FirebaseAuthenticator instance;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public Authenticator getInstance(){
        if (instance == null) {
            instance = new FirebaseAuthenticator();
        }
        return instance;
    }
    @Override
    public User getCurrentUser() {
        if(mAuth.getCurrentUser() == null){
            return null;//new User(0,"aa","dd","dd");
        }

        return new UserFirebaseImpl(mAuth.getCurrentUser());
    }

    @Override
    public void logOut() {
        mAuth.signOut();
    }

    public Task<AuthResult> autheticateWithCredential(AuthCredential authCredential) {
        return mAuth.signInWithCredential(authCredential);
    }
}
