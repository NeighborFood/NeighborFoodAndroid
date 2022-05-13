package com.epfl.neighborfood.neighborfoodandroid.authentication;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.FirebaseAuthenticatorUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * A firebase implementation of the authenticator
 */
public class FirebaseAuthenticator implements Authenticator {
    private static FirebaseAuthenticator instance;
    private static FirebaseAuth mAuth ;
    private FirebaseAuthenticator(){

    }
    public static FirebaseAuthenticator getInstance(){
        if(instance == null){
            mAuth= FirebaseAuth.getInstance();
            instance = new FirebaseAuthenticator();
        }
        return instance;
    }
    @Override
    public AuthenticatorUser getCurrentAuthUser() {
        if(mAuth.getCurrentUser() == null){
            return null;
        }
        return new FirebaseAuthenticatorUser(mAuth.getCurrentUser()) ;
    }

    @Override
    public void addAuthStateChangeListener(AuthUserStateChangeListener listener) {
        mAuth.addAuthStateListener(firebaseAuth -> listener.onAuthStateChanged());
    }

    @Override
    public void logOut() {
        mAuth.signOut();
    }

    @Override
    public Task<Void> logInWithGoogleAccount(GoogleSignInAccount googleAccount) {
        String googleTokenId = googleAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null);
        return mAuth.signInWithCredential(googleAuthCredential).continueWith(task -> null);
    }


}
