package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;

import java.util.List;

public class ConversationRepositoryImplementation extends ConversationRepository{

    @Override
    Conversation getConversation(String conversationID) {
        return null;
    }

    @Override
    List<Conversation> getAllConversations() {
        return null;
    }

    @Override
    void addConversation(Conversation conv) {

    }

    @Override
    void updateConversation(Conversation conv, String conversationID) {

    }

    @Override
    void removeConversation(String conversationID) {

    }
}
