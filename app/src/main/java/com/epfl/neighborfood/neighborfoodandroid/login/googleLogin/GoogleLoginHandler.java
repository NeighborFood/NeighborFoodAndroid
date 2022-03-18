package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import android.content.Intent;

import com.epfl.neighborfood.neighborfoodandroid.login.Account;
import com.epfl.neighborfood.neighborfoodandroid.login.LoginHandler;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class GoogleLoginHandler implements LoginHandler {

    @Override
    public Account handleOnLoginIntentResult(int requestCode, Intent intent) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
        GoogleSignInAccount account = null;
        try{
            task.getResult(ApiException.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return new GoogleAccount(account);
    }
}
