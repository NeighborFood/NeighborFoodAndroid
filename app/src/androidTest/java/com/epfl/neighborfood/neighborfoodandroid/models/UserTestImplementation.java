package com.epfl.neighborfood.neighborfoodandroid.models;

import java.util.ArrayList;

public class UserTestImplementation extends User{
    /**
     * Create a new User object, holding database
     * information related to the user.
     * Most of values are given by tequila.
     *
     * @param id        ID of the user in database
     * @param email     E-mail of user
     * @param firstName First name of user
     * @param lastName  Last name of user
     */
    public UserTestImplementation(String id, String email, String firstName, String lastName) {
        super(id, email, firstName, lastName,"");
        setBio("");
        setLinks(new ArrayList<>());
    }
}
