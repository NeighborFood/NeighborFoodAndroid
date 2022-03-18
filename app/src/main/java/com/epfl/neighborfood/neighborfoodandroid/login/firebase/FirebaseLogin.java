package com.epfl.neighborfood.neighborfoodandroid.login.firebase;

import androidx.annotation.NonNull;

import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseLogin {
    private FirebaseAuth mAuth;

    public FirebaseLogin(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void loginWithCredential(AuthCredential authCredential, SignUpActivity signUpActivity) {
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(signUpActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    signUpActivity.updateUI(user);
                }
            }
        });
    }

    public void signOut() {
        mAuth.signOut();
    }

    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

}
