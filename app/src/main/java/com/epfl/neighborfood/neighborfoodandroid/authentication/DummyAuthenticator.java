package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;

public class DummyAuthenticator implements Authenticator{

    private static DummyAuthenticator instance;

    public static DummyAuthenticator getInstance(){
        if (instance == null) {
            instance = new DummyAuthenticator();
        }
        return instance;
    }

    public User getCurrentUser(){
       return new User(-1,"me@epfl.ch","Me", "Notyou");
    }

}
