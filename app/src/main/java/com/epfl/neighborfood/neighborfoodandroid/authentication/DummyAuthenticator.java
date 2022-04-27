package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;


public class DummyAuthenticator implements Authenticator {
    public User getCurrentUser() {
        return new User("-1", "me@epfl.ch", "Me", "Notyou");
    }

    @Override
    public void logOut() {

    }

    @Override
    public Task logInWithGoogleAccount(GoogleSignInAccount googleAccount) {
        //TODO: Change implementation with mockito
        return null;
    }


}
