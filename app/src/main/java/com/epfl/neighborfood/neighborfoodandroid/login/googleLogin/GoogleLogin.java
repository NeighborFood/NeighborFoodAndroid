package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import android.content.Intent;


import com.epfl.neighborfood.neighborfoodandroid.login.Login;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;


public class GoogleLogin implements Login {


    private GoogleSignInClient mGoogleSignInClient;

    public GoogleLogin(GoogleSignInClient mGoogleSignInClient) {
        this.mGoogleSignInClient = mGoogleSignInClient;
    }

    @Override
    public Intent signIn() {
        return mGoogleSignInClient.getSignInIntent();
    }

    @Override
    public void signOut() {
        mGoogleSignInClient.signOut();
    }


}
