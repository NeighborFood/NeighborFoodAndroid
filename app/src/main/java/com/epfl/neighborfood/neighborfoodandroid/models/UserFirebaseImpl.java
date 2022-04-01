package com.epfl.neighborfood.neighborfoodandroid.models;


import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

public class UserFirebaseImpl extends User {
    private FirebaseUser firebaseUser;
    /**
     * create a LoggedInUser instance from a FirebaseUser
     * @param firebaseUser(FirebaseUser)
     */
    public UserFirebaseImpl(com.google.firebase.auth.FirebaseUser firebaseUser) {
        super(0,firebaseUser.getEmail(),firebaseUser.getDisplayName(),firebaseUser.getDisplayName());
        this.firebaseUser = firebaseUser;
    }

    public UserFirebaseImpl(String fakeName, String email) {
        super(0,email,fakeName,fakeName);

    }

    @Override
    public Uri getProfilePictureURI() {
        return firebaseUser.getPhotoUrl();
    }
}

