package com.epfl.neighborfood.neighborfoodandroid.authentication;

import android.net.Uri;

import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;


public class DummyAuthenticator implements Authenticator {
    private static DummyAuthenticator instance;
    private DummyAuthenticator(){

    }
    public static DummyAuthenticator getInstance(){
        if(instance==null){
            instance = new DummyAuthenticator();
        }
        return instance ;
    }

    @Override
    public AuthenticatorUser getCurrentAuthUser() {
        return new AuthenticatorUser("-1", "me@epfl.ch", "Me", "Notyou", ""){};
    }

    public User getCurrentUser() {
        return new User("-1", "me@epfl.ch", "Me", "Notyou", ""){};
    }

    @Override
    public void addAuthStateChangeListener(AuthUserStateChangeListener listener) {

    }

    @Override
    public void logOut() {

    }

    @Override
    public Task<Void> logInWithGoogleAccount(GoogleSignInAccount googleAccount) {
        return Tasks.forResult(null);
    }


}
