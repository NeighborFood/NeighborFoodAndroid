package com.epfl.neighborfood.neighborfoodandroid.authentication;

import com.epfl.neighborfood.neighborfoodandroid.models.User;

/**
 * Authentication interface
 */
public interface Authenticator {

    /**
     * Returns the user currently signed in
     * @return user
     */
    User getCurrentUser();

    void logOut();







}