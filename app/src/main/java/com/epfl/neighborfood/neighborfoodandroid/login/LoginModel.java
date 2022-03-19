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

    public Intent signIn(){
        return login.signIn();
    }

    public void signOut(){
        login.signOut();
        firebaseLogin.signOut();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LoginHandler getLoginHandler() {
        return loginHandler;
    }

    public void setLoginHandler(LoginHandler loginHandler) {
        this.loginHandler = loginHandler;
    }

    public FirebaseLogin getFirebaseLogin() {
        return firebaseLogin;
    }

    public void setFirebaseLogin(FirebaseLogin firebaseLogin) {
        this.firebaseLogin = firebaseLogin;
    }
}
