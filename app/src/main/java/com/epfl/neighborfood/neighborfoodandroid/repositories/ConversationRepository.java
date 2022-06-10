package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseSingleton;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains
 */
public class ConversationRepository {
    private final static String CONVERSATIONS_COLLECTION_PATH = "Conversations";

    public ConversationRepository() {

    }


    /**
     * fetches a particular conversation from the database
     *
     * @param conversationID Id of a conversation
     * @return Task containing the conversation fetched
     */
    public Task<Conversation> getConversation(String conversationID) {
        return DatabaseSingleton.getDependency().fetch(CONVERSATIONS_COLLECTION_PATH, conversationID).continueWith(t -> t.getResult().toModel(Conversation.class));
    }

    /**
     * Fetches all conversations for the given user
     *
     * @param userID : the user id
     * @return the list of conversations the user is part of
     */
    public Task<List<Conversation>> getAllConversations(String userID) {
        return DatabaseSingleton.getDependency().fetchAllArrayAttributeContains(CONVERSATIONS_COLLECTION_PATH, "users", userID).continueWith(
                t -> {
                    List<Conversation> res = new ArrayList<>();
                    List<DocumentSnapshot> ds = t.getResult();
                    for (DocumentSnapshot doc : ds
                    ) {
                        res.add(doc.toModel(Conversation.class));
                    }
                    return res;
                }
        );
    }

    /**
     * Adds a conversation with an Id to the database
     *
     * @param id   id of conversation to be set
     * @param conv the conversation to be added
     * @return a task containing the conversation added
     */
    public Task<Conversation> addConversation(String id, Conversation conv) {
        return DatabaseSingleton.getDependency().set(CONVERSATIONS_COLLECTION_PATH, id, conv).continueWith(t -> conv);
    }

    /**
     * Updates an existing conversation in the database
     *
     * @param conv           conversation to be updated
     * @param conversationID Id of the conversation to be updated
     * @return a task containing the updated conversation
     */
    public Task<Void> updateConversation(Conversation conv, String conversationID) {
        return DatabaseSingleton.getDependency().set(CONVERSATIONS_COLLECTION_PATH, conversationID, conv);
    }

    /**
     * notifies listener when updates happen in a conversation
     *
     * @param conversationID ID of conversation
     * @param listener       listener to the conversation updates
     */
    public void addOnConversationChangeListener(String conversationID, Database.ModelUpdateListener listener) {
        DatabaseSingleton.getDependency().addChangesListener(CONVERSATIONS_COLLECTION_PATH, conversationID, listener);
    }


}
