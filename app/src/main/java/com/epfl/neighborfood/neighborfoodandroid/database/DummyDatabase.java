package com.epfl.neighborfood.neighborfoodandroid.database;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DummyDatabase implements Database {

    private final List<Message> chatDB = new ArrayList<>();
    private final ArrayList<Conversation> conversationsDB = new ArrayList<>();
    private static DummyDatabase instance;
    public static final int PROFILE_IMG_ID = R.drawable.profile_img_male;


    public static DummyDatabase getInstance() {
        if (instance == null) {
            instance = new DummyDatabase();
        }
        return instance;
    }

    public void pushMessage(Message message) {
        chatDB.add(message);
    }

    public List<Message> fetchMessages() {
        return chatDB;
    }

    public void pushConversation(Conversation conversation) {
        conversationsDB.add(conversation);
    }

    public ArrayList<Conversation> fetchConversations() {
        return conversationsDB;
    }

    public List<Message> fetchUserConversation(User chatter){
        if (chatter != null){
            for (Conversation conv: conversationsDB){
                if (chatter.getId()==conv.getChatter().getId()){
                    return conv.getMessages();
                }
            }

        }
        return new ArrayList<>();
    }


    public void reset() {
        chatDB.removeAll(chatDB);
        conversationsDB.removeAll(conversationsDB);
    }

}
