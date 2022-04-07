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
        super(firebaseUser.getUid(),firebaseUser.getEmail(),firebaseUser.getDisplayName(),firebaseUser.getDisplayName());
        this.firebaseUser = firebaseUser;
        String[] nameLastName = getNameLastNameFromDisplayName(firebaseUser == null ? "Flen Fouleni":firebaseUser.getDisplayName());
        setFirstName(nameLastName[0]);
        setLastName(nameLastName[1]);
    }

    private String[] getNameLastNameFromDisplayName(String str){
        String[] res = new String[2];
        String[] split = str.split(" ");
        res[0] = split.length>0 ? split[0] : "";
        res[1] = split.length>1 ? split[1] : "";

        return res;
    }

    @Override
    public Uri getProfilePictureURI() {
        return firebaseUser==null? Uri.EMPTY : firebaseUser.getPhotoUrl();
    }
}

