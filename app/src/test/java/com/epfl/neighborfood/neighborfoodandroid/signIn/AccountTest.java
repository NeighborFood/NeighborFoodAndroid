package com.epfl.neighborfood.neighborfoodandroid.signIn;

import static org.junit.Assert.assertEquals;

import com.epfl.neighborfood.neighborfoodandroid.login.Account;

import org.junit.Test;

public class AccountTest {

    @Test
    public void accountConstructorSetsTheRightFields(){
        String firstName = "NeighborFood";
        String lastName = "Android";
        String email = "NeighborFood@gmail.com";
        Account testAccount = new Account(firstName, lastName, email);
        assertEquals(firstName, testAccount.getFirstName());
        assertEquals(lastName, testAccount.getLastName());
        assertEquals(email, testAccount.getEmail());
    }
}
