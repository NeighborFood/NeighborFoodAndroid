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
    public static LoggedInUser createLoggedInInUserFromFirebaseUser(FirebaseUser firebaseUser) {
        if(firebaseUser == null) {
            return null;
        }
        return new LoggedInUser(firebaseUser.getDisplayName(), firebaseUser.getEmail());
    }
    

    @Override
    public String toString() {
        return name + " ";
    }
}

