package com.epfl.neighborfood.neighborfoodandroid;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleLogin implements Login {

    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 100;
    private GoogleSignInClient mGoogleSignInClient;
    private AppCompatActivity loginActivity;
    private FirebaseUser user;

    public GoogleLogin(AppCompatActivity loginActivity) {
        this.loginActivity = loginActivity;
        mAuth = FirebaseAuth.getInstance();
        activateGoogleSignIn();
    }

    @Override
    public Intent signIn() {
        return mGoogleSignInClient.getSignInIntent();
    }

    @Override
    public void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut();
    }

    public void onLoginResult(int requestCode, int resultCode, Intent data){
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task){
        try{
            GoogleSignInAccount account = task.getResult(ApiException.class);
            FirebaseAuthWithGoogle(account);
        }
        catch(ApiException apiE){
            FirebaseAuthWithGoogle(null);
        }
    }

    private void FirebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    update(user);
                }
            }
        });

    }

    //configuring googleSignIn
    private void activateGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("743325402792-a1337fr4929vml06r8pkrtaasubvpusp.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(loginActivity, gso);
    }

    public int getRC_SIGN_IN() {
        return RC_SIGN_IN;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void update(FirebaseUser user){

    }
}
