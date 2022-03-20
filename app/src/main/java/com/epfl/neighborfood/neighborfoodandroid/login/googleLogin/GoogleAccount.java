package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import com.epfl.neighborfood.neighborfoodandroid.login.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


public class GoogleAccount extends Account {
    // the google signIn account assigned to this google account
    private GoogleSignInAccount googleSignInAccount;

    /**
     * @param googleAccount the signIn google account associated to create this Account
     */
    public GoogleAccount(GoogleSignInAccount googleAccount){
        super(googleAccount.getDisplayName(), googleAccount.getFamilyName(), googleAccount.getEmail());
        this.googleSignInAccount = googleAccount;
    }

    /**
     * @return the authentication credential of the signIn google account
     */
    public AuthCredential getAccountCredential(){
        return GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
    }
}
