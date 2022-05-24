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
        DummyAuthenticator.getInstance().setAuthUser(new AuthenticatorUser("-1","","","","") {
        });
        User usr = new User("1",null,null,null,"");
        Set<User> chatters = new HashSet<User>();
        chatters.add(usr);
        chatters.add(DummyAuthenticator.getInstance().getCurrentUser());
        Conversation conversation = new Conversation(chatters,new ArrayList<>());
        //assertThat(conversation.chatter().getId(),equalTo(usr.getId()));
    }

    @Test
    public void getMessagesTest(){
        Message m1 = new Message("Hi",null,null);
        Message m2 = new Message("Hello",null,null);
        ArrayList<Message> msgs = new ArrayList<>();
        msgs.add(m1);
        msgs.add(m2);

        Conversation conversation = new Conversation(null,msgs);
        //assertThat(conversation.getMessages().get(0).getContent(),equalTo("Hi"));
        //assertThat(conversation.getMessages().get(1).getContent(),equalTo("Hello"));
    }
}
