package com.epfl.neighborfood.neighborfoodandroid.login;

import com.google.firebase.auth.FirebaseUser;

public class LoggedInUser {

    // the logged in user full name
    private String name;
    // the logged in user email
    private String email;

    public LoggedInUser(String firstName, String email) {
        this.name = firstName;
        this.email = email;
    }

    /**
     * create a LoggedInUser instance from a FirebaseUser
     * @param firebaseUser(FirebaseUser)
     */
    public LoggedInUser(FirebaseUser firebaseUser) {
       name = firebaseUser.getDisplayName();
       email = firebaseUser.getEmail();
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name + " ";
    }
}

