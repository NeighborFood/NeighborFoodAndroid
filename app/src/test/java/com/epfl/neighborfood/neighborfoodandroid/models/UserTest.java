package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
public class UserTest {

    @Test
    public void getIdTest(){
        String id = "1" ;
        User user = new User(id,null,null,null);
        assertThat(user.getId(),equalTo(id));
    }
    @Test
    public void setIdTest(){
        String id = "1" ;
        User user = new User("0",null,null,null);
        user.setId(id);
        assertThat(user.getId(),equalTo(id));
    }

    @Test
    public void getEmailTest(){
        String email = "toto@epfl.ch";
        User user = new User("0",email,null,null);
        assertThat(user.getEmail(),equalTo(email));
    }


    @Test
    public void setEmailTest(){
        String email = "toto@epfl.ch";
        User user = new User("0",null,null,null);
        user.setEmail(email);
        assertThat(user.getEmail(),equalTo(email));
    }

    @Test
    public void getFirstNameTest(){
        String name = "toto";
        User user = new User("0",null,name,null);
        assertThat(user.getFirstName(),equalTo(name));
    }


    @Test
    public void setFirstNameTest(){
        String name = "toto";
        User user = new User("0",null,null,null);
        user.setFirstName(name);
        assertThat(user.getFirstName(),equalTo(name));
    }


    @Test
    public void getLastNameTest(){
        String name = "toto";
        User user = new User("0",null,null,name);
        assertThat(user.getLastName(),equalTo(name));
    }


    @Test
    public void setLastNameTest(){
        String name = "toto";
        User user = new User("0",null,null,null);
        user.setLastName(name);
        assertThat(user.getLastName(),equalTo(name));
    }

    @Test
    public void getFullNameTest(){
        String fn = "toto";
        String ln = "titi";
        String name = fn+" "+ln;
        User user = new User("0",null,fn,ln);
        assertThat(user.getUsername(),equalTo(name));
    }

    @Test
    public void toStringTest(){
        String id = "1";
        String email = "toto@epfl.ch";
        String firstName = "toto";
        String lastName = "titi";
        User user = new User(id,email,firstName,lastName);

        String result = "{\n" +
                "id" + " : " + id + ", " + "\n" +
                "email" + " : " + email + ", " + "\n" +
                "firstname" + " : " + firstName + ", " + "\n" +
                "lastname" + " : " + lastName + " " + "\n" +
                "}";
        assertThat(user.toString(),equalTo(result));
    }


}
