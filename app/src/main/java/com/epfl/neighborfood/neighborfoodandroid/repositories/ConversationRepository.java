package com.epfl.neighborfood.neighborfoodandroid.repositories;

import androidx.lifecycle.MutableLiveData;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;

import java.util.List;


public abstract class ConversationRepository {
    public MutableLiveData<List<Conversation>> conversations;

    public ConversationRepository() {
        this.conversations = new MutableLiveData<>();
    }


    public abstract Conversation getConversation(String conversationID);
    public abstract List<Conversation> getAllConversations();
    public abstract void addConversation(Conversation conv);
    public abstract void updateConversation(Conversation conv,String conversationID);
    public abstract void removeConversation(String conversationID);


}
