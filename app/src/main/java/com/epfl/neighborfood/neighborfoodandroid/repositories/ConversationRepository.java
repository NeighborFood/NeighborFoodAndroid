package com.epfl.neighborfood.neighborfoodandroid.repositories;

import androidx.lifecycle.MutableLiveData;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;

import java.util.List;


public abstract class ConversationRepository {
    protected MutableLiveData<List<Conversation>> conversations;

    public ConversationRepository() {
        this.conversations = new MutableLiveData<>(getAllConversations());
    }


    abstract Conversation getConversation(String conversationID);
    abstract List<Conversation> getAllConversations();
    abstract void addConversation(Conversation conv);
    abstract void updateConversation(Conversation conv,String conversationID);
    abstract void removeConversation(String conversationID);


}
