package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import java.util.Date;


public class MessageTest {

    @Test
    public void getContentTest(){
        String txt = "test";
        Message msg = new Message(txt,null,null);
        assertThat(msg.getContent(),equalTo(txt));
    }

    @Test
    public void setContentTest(){
        String txt = "test";
        Message msg = new Message(null,null,null);
        msg.setContent(txt);
        assertThat(msg.getContent(),equalTo(txt));
    }

    @Test
    public void getSenderTest(){
        String id = "1" ;
        User sender = new User(id,null,null,null,null);
        Message msg = new Message(null,sender.getId(),null);
        assertThat(msg.getSender(),equalTo(id));
    }


    @Test
    public void setSenderTest(){
        String id = "1" ;
        User sender = new User(id,null,null,null,null);
        Message msg = new Message(null,null,null);
        msg.setSender(sender.getId());
        assertThat(msg.getSender(),equalTo(id));
    }


    @Test
    public void getDateTest(){
        Date date = new Date();
        Message msg = new Message(null,null,date);
        assertThat(msg.getDate(),equalTo(date));
    }

    @Test
    public void setDateTest(){
        Date date = new Date();
        Message msg = new Message(null,null,null);
        msg.setDate(date);
        assertThat(msg.getDate(),equalTo(date));
    }

}
