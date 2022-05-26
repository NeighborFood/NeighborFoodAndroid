package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConversationTest {
    @Before
    public void setup(){
    }

    @Test
    public void getChatterTest(){
        List<String> chatters = new ArrayList<>();
        chatters.add("1");
        chatters.add("2");
        Conversation conversation = new Conversation("1-2",chatters,new ArrayList<>());
        assertThat(conversation.chatter("1"),equalTo("2"));
    }

    @Test
    public void getMessagesTest(){
        Message m1 = new Message("Hi",null,null);
        Message m2 = new Message("Hello",null,null);
        ArrayList<Message> msgs = new ArrayList<>();
        msgs.add(m1);
        msgs.add(m2);

        Conversation conversation = new Conversation("",null,msgs);
        assertThat(conversation.getMessages().get(0).getContent(),equalTo("Hi"));
        assertThat(conversation.getMessages().get(1).getContent(),equalTo("Hello"));
    }
}
