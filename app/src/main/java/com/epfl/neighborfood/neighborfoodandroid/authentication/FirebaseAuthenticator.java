package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.models.UserFirebaseImpl;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class FirebaseAuthenticator implements Authenticator {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public User getCurrentUser() {
        if (mAuth.getCurrentUser() == null) {
            return null;
        }

        return new UserFirebaseImpl(mAuth.getCurrentUser());
    }

    @Override
    public void logOut() {
        mAuth.signOut();
    }

    @Override
    public Task logInWithGoogleAccount(GoogleSignInAccount googleAccount) {
        String googleTokenId = googleAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null);
        return mAuth.signInWithCredential(googleAuthCredential);
    }

}
