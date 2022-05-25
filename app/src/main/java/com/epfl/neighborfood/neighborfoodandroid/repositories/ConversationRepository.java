package com.epfl.neighborfood.neighborfoodandroid.repositories;

import androidx.lifecycle.MutableLiveData;

import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


public class ConversationRepository {
    private final static String CONVERSATIONS_COLLECTION_PATH = "Conversations";

    public ConversationRepository() {

    }


    /** fetches a particular conversation from the database
     * @param conversationID
     * @return
     */
    public Task<Conversation> getConversation(String conversationID) {
        return DatabaseFactory.getDependency().fetch(CONVERSATIONS_COLLECTION_PATH,conversationID).continueWith(t->t.getResult().toModel(Conversation.class));
    }

    /** Fetches all conversations for the given user
     * @param userID : the user id
     * @return the list of conversations the user is part of
     */
    public Task<List<Conversation>> getAllConversations(String userID) {
        return DatabaseFactory.getDependency().fetchAllArrayAttributeContains(CONVERSATIONS_COLLECTION_PATH,"users",userID).continueWith(
                t->{
                    List<Conversation> res = new ArrayList<>();
                    List<DocumentSnapshot> ds = t.getResult();
                    for (DocumentSnapshot doc: ds
                         ) {
                        res.add(doc.toModel(Conversation.class));
                    }
                    return res;
                }
        );
    }

    public Task<Conversation> addConversation(String id,Conversation conv) {
        return DatabaseFactory.getDependency().set(CONVERSATIONS_COLLECTION_PATH,id,conv).continueWith(t->conv);
    }

    public Task<Void> updateConversation(Conversation conv, String conversationID) {
        return DatabaseFactory.getDependency().set(CONVERSATIONS_COLLECTION_PATH,conversationID,conv);
    }

    public void removeConversation(String conversationID) {

    }
    public void addOnConversationChangeListener(String conversationID, Database.ModelUpdateListener listener){
        DatabaseFactory.getDependency().addChangesListener(CONVERSATIONS_COLLECTION_PATH,conversationID,listener);
    }


}
