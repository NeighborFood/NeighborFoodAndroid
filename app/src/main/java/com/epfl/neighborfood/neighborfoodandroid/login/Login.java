package com.epfl.neighborfood.neighborfoodandroid.login;

import android.content.Intent;

public interface Login {

    /**
     * @return an Intent that will generate the action of the login
     */
    Intent signIn();

    /**
     * log out from different parties contributing to log in an account
     */
    void signOut();

}

