package com.epfl.neighborfood.neighborfoodandroid.database.dummy;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;
import java.util.List;


public class DummyDatabase implements Database {

    private final List<Message> chatDB = new ArrayList<>();
    private final ArrayList<Conversation> conversationsDB = new ArrayList<>();
    private static DummyDatabase instance;
    public static final int PROFILE_IMG_ID = R.drawable.profile_img_male;

    private DummyDatabase(){
    }


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
                if (chatter.getId()==conv.chatter().getId()){
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

    @Override
    public Task<DocumentSnapshot> fetch(String collectionPath, String documentPath) {
        return null;
    }

    @Override
    public Task<Void> set(String collectionPath, String documentPath, Object data) {
        return null;
    }

    @Override
    public Task<Void> delete(String collectionPath, String documentPath) {
        return null;
    }

    @Override
    public Task<String> add(String collectionPath, Object data) {
        return null;
    }

    @Override
    public Task<CollectionSnapshot> fetchAll(String collectionPath) {
        return null;
    }
}
