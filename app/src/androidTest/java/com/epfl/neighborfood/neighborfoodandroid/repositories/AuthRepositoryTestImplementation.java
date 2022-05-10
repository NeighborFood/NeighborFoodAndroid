package com.epfl.neighborfood.neighborfoodandroid.repositories;

import android.os.Handler;

import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class AuthRepositoryTestImplementation extends AuthRepository{
    private AuthenticatorUser authUser;
    @Override
    public Task<AuthenticatorUser> logInWithGoogleAccount(GoogleSignInAccount account) {
        return Tasks.forResult(AuthenticatorFactory.getDependency().getCurrentAuthUser());
    }

    /** Sets directly the currently authenticated user
     * @param user : the user to put as the logged in user
     */
    public void setUser(User user){
    }
}
