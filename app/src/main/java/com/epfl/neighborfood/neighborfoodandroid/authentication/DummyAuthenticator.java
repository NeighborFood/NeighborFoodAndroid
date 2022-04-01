package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;

public class DummyAuthenticator implements Authenticator{
    private static DummyAuthenticator instance = new DummyAuthenticator();

    public static Authenticator getInstance() {
        return instance;
    }

    public User getCurrentUser(){
       return new User(-1,"me@epfl.ch","Me", "Notyou");
    }

    @Override
    public void logOut() {

    }


}
