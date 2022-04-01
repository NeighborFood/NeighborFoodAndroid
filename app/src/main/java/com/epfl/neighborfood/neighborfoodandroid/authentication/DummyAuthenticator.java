package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;

public class DummyAuthenticator implements Authenticator{


    public User getCurrentUser(){
       return new User(-1,"me@epfl.ch","Me", "Notyou");
    }

    @Override
    public void logOut() {

    }


}
