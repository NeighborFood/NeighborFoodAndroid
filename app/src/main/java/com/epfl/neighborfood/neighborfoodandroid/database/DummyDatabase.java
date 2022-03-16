package com.epfl.neighborfood.neighborfoodandroid.database;

import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DummyDatabase implements Database{

    private final List<Message> chatDB = new ArrayList<>();
    private static DummyDatabase instance;


    public static DummyDatabase getInstance(){
        if (instance==null) {
            instance = new DummyDatabase();
        }
            return instance ;
    }

    public void pushMessage(Message message){
        chatDB.add(message);
    }


    public List<Message> fetchMessages(){
        return chatDB;
    }

    public void reset(){
        chatDB.removeAll(chatDB);
    }

}
