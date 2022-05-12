package com.epfl.neighborfood.neighborfoodandroid.repositories;

import androidx.lifecycle.MutableLiveData;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;

import java.util.List;


public class ConversationRepository {
    public MutableLiveData<List<Conversation>> conversations;

    public ConversationRepository() {
        this.conversations = new MutableLiveData<>();
    }


    public Conversation getConversation(String conversationID) {
        return null;
    }

    public List<Conversation> getAllConversations() {
        return null;
    }

    public void addConversation(Conversation conv) {

    }

    public void updateConversation(Conversation conv, String conversationID) {
    }

    public void removeConversation(String conversationID) {

    }


}
