package com.epfl.neighborfood.neighborfoodandroid.authentication;

import android.net.Uri;

import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;


public class DummyAuthenticator implements Authenticator {
    private static DummyAuthenticator instance;
    private AuthenticatorUser fakeAuthUser;
    private User user;
    private final ArrayList<AuthUserStateChangeListener> listeners;
    private DummyAuthenticator(){
        listeners = new ArrayList<>();
    }
    public static DummyAuthenticator getInstance(){
        if(instance==null){
            instance = new DummyAuthenticator();
        }
        return instance ;
    }

    @Override
    public AuthenticatorUser getCurrentAuthUser() {
        return fakeAuthUser;
    }

    public User getCurrentUser() {
        return this.user;
    }

    @Override
    public void addAuthStateChangeListener(AuthUserStateChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void logOut() {
        fakeAuthUser = null;
        user = null;
        notifyUpdate();
    }
    private void notifyUpdate(){
        for (AuthUserStateChangeListener l:
                listeners) {
            l.onAuthStateChanged();
        }
    }

    @Override
    public Task<Void> logInWithGoogleAccount(GoogleSignInAccount googleAccount) {
        this.fakeAuthUser = new AuthenticatorUser("-1","","","","") {
        };
        this.user =new User("-1", "me@epfl.ch", "Me", "Notyou", "");
        notifyUpdate();
        return Tasks.forResult(null);
    }

    public void setAuthUser(AuthenticatorUser user){
        this.fakeAuthUser = user;
        this.user = new User(user.getId(),user.getEmail(),user.getFirstName(),user.getLastName(),user.getPpUri());
        notifyUpdate();
    }



}
