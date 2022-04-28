package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;


public class DummyAuthenticator implements Authenticator {
    public User getCurrentUser() {
        return new User("-1", "me@epfl.ch", "Me", "Notyou");
    }

    @Override
    public void logOut() {

    }

    @Override
    public Task<Void> logInWithGoogleAccount(GoogleSignInAccount googleAccount) {
        return Tasks.forResult(null);
    }


}
