package com.epfl.neighborfood.neighborfoodandroid.models;


import com.google.firebase.auth.FirebaseUser;

/**
 * A firebase authenticator user
 */
public class FirebaseAuthenticatorUser extends AuthenticatorUser {
    private final FirebaseUser firebaseUser;

    /**
     * create a LoggedInUser instance from a FirebaseUser
     *
     * @param firebaseUser(FirebaseUser) : the user returned by firebase
     */
    public FirebaseAuthenticatorUser(com.google.firebase.auth.FirebaseUser firebaseUser) {
        super(firebaseUser.getUid(), firebaseUser.getEmail(), firebaseUser.getDisplayName(), firebaseUser.getDisplayName(), firebaseUser.getPhotoUrl().toString());
        this.firebaseUser = firebaseUser;
        String[] nameLastName = getNameLastNameFromDisplayName(firebaseUser.getDisplayName());
        setFirstName(nameLastName[0]);
        setLastName(nameLastName[1]);
    }


    /**
     * Separates the first name from the lastname of the user
     *
     * @param str the string containing the full name
     * @return a list containing the first name and the lastname of the user, if not found, it will be an empty string
     */
    private String[] getNameLastNameFromDisplayName(String str) {
        String[] res = new String[2];
        String[] split = str.split(" ");
        res[0] = split.length > 0 ? split[0] : "";
        res[1] = split.length > 1 ? split[1] : "";

        return res;
    }
}

