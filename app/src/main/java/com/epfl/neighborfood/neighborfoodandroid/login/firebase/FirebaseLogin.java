package com.epfl.neighborfood.neighborfoodandroid.login.firebase;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.epfl.neighborfood.neighborfoodandroid.models.UserFirebaseImpl;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class FirebaseLogin {

    //the firebase authenticator
    private FirebaseAuth mAuth;

    public FirebaseLogin(){
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * login into the firebase using the authenticator and the authentication credential
     * @param authCredential(AuthCredential): the authentication credential (in Google signIn case they can be extracted from the GoogleSignInAccount)
     */
    public void loginWithCredential(AuthCredential authCredential) {
        mAuth.signInWithCredential(authCredential).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    com.google.firebase.auth.FirebaseUser user = mAuth.getCurrentUser();

                }
            }
        });
    }

    /**
     * log out the firebase authenticator
     */
    public void signOut() {
        mAuth.signOut();
    }

    /**
     * @return the current authenticated user into the firebase authenticator
     */
    public com.google.firebase.auth.FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

}
