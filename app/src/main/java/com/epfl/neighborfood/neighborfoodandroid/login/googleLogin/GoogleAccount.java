package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import com.epfl.neighborfood.neighborfoodandroid.login.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleAccount extends Account {
    private GoogleSignInAccount googleSignInAccount;

    public GoogleAccount(GoogleSignInAccount googleAccount){
        super(googleAccount.getDisplayName(), googleAccount.getFamilyName(), googleAccount.getEmail());
        this.googleSignInAccount = googleAccount;
    }

    public AuthCredential getAccountCredential(){
        return GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
    }
}
