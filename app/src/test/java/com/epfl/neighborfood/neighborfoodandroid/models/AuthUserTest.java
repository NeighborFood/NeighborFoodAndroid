package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class AuthUserTest {
    @Test
    public void getIdTest(){
        String id = "1" ;
        AuthenticatorUser user = new AuthenticatorUser(id, null, null, null, null) {
        };
        assertThat(user.getId(),equalTo(id));
    }

    @Test
    public void getEmailTest(){
        String email = "toto@epfl.ch";
        AuthenticatorUser user = new AuthenticatorUser("0", email, null, null, null) {
        };
        assertThat(user.getEmail(),equalTo(email));
    }



    @Test
    public void getFirstNameTest(){
        String name = "toto";
        AuthenticatorUser user = new AuthenticatorUser("0", null, name, null, null) {
        };
        assertThat(user.getFirstName(),equalTo(name));
    }


    @Test
    public void setFirstNameTest(){
        String name = "toto";
        AuthenticatorUser user = new AuthenticatorUser("0", null, null, null, null) {
        };
        user.setFirstName(name);
        assertThat(user.getFirstName(),equalTo(name));
    }


    @Test
    public void getLastNameTest(){
        String name = "toto";
        AuthenticatorUser user = new AuthenticatorUser("0", null, null, name, null) {
        };
        assertThat(user.getLastName(),equalTo(name));
    }


    @Test
    public void setLastNameTest(){
        String name = "toto";
        AuthenticatorUser user = new AuthenticatorUser("0", null, null, null, null) {
        };
        user.setLastName(name);
        assertThat(user.getLastName(),equalTo(name));
    }
    @Test
    public void getPPUriTest(){
        AuthenticatorUser user = new AuthenticatorUser("0", null, null, null, "empty") {
        };
        assertThat(user.getPpUri(), equalTo("empty"));
    }



}
