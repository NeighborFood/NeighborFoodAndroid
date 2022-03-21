package com.epfl.neighborfood.neighborfoodandroid.models;

import java.util.List;

public class Conversation extends Model{
    private final User chatter ;
    private final List<Message> messages;


    public Conversation(User chatter, List<Message> messages) {
        this.chatter = chatter;
        this.messages = messages;
    }

    public User getChatter() {
        return chatter;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message getLastMessage(){
        if (messages != null){
            return messages.get(messages.size()-1);
        }
        return null;
    }
}
