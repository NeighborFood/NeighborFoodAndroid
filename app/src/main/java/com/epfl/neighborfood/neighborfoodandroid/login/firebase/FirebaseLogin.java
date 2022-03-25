package com.epfl.neighborfood.neighborfoodandroid.login.firebase;

import androidx.annotation.NonNull;

import com.epfl.neighborfood.neighborfoodandroid.login.LoggedInUser;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirebaseLogin {

    //the firebase authenticator
    private FirebaseAuth mAuth;

    public FirebaseLogin(){
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * login into the firebase using the authenticator and the authentication credential
     * @param authCredential(AuthCredential): the authentication credential (in Google signIn case they can be extracted from the GoogleSignInAccount)
     * @param signUpActivity(SignUpActivity): the login activity
     */
    public void loginWithCredential(AuthCredential authCredential, SignUpActivity signUpActivity) {
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(signUpActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    signUpActivity.updateUI(LoggedInUser.createLoggedInInUserFromFirebaseUser((user)));
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
    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

}
