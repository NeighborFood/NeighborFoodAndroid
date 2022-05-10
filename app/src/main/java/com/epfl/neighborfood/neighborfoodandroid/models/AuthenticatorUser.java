package com.epfl.neighborfood.neighborfoodandroid.models;

import android.net.Uri;

public abstract class AuthenticatorUser {
    private final String id ;
    private final String email;
    private String firstName ;
    private String lastName ;
    private final String ppUri;
    public AuthenticatorUser(String id, String email, String firstName, String lastName, String ppUri){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ppUri = ppUri;
    }

    public String getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPpUri(){
        return ppUri;
    }

}
