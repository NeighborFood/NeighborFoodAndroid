package com.epfl.neighborfood.neighborfoodandroid.login;

import com.google.firebase.auth.FirebaseUser;

public class LoggedInUser {

    private String firstName;
    private String email;

    public LoggedInUser(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public LoggedInUser(FirebaseUser firebaseUser) {
       firstName = firebaseUser.getDisplayName();
       email = firebaseUser.getEmail();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " ";
    }
}

