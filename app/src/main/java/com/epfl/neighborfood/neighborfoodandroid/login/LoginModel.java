package com.epfl.neighborfood.neighborfoodandroid.login;

import android.content.Intent;

import com.epfl.neighborfood.neighborfoodandroid.login.firebase.FirebaseLogin;

public class LoginModel {
    private Account account;
    private Login login;
    private LoginHandler loginHandler;
    private FirebaseLogin firebaseLogin;

    public LoginModel(Login login, LoginHandler loginHandler, FirebaseLogin firebaseLogin) {
        this.login = login;
        this.loginHandler = loginHandler;
        this.firebaseLogin = firebaseLogin;
    }

    /**
     * Uses the Login object to sign in by generating an sign in intent
     * @return a sign in intent (Intent)
     */
    public Intent signIn(){
        return login.signIn();
    }

    /**
     * Log out all different parties contributing to the sign in action
     */
    public void signOut(){
        login.signOut();
        firebaseLogin.signOut();
    }

    public LoginHandler getLoginHandler() {
        return loginHandler;
    }

    public FirebaseLogin getFirebaseLogin() {
        return firebaseLogin;
    }

}
