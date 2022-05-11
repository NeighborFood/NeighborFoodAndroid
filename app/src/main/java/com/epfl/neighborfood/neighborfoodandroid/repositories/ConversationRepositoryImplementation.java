package com.epfl.neighborfood.neighborfoodandroid.repositories;

import androidx.lifecycle.MutableLiveData;

import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Continuation;


import java.util.ArrayList;
import java.util.List;

public class ConversationRepositoryImplementation extends ConversationRepository{

    //private final String collectionReference = "conversations";
    private final String collectionReference = "test";



    public ConversationRepositoryImplementation(){
        super();
        conversations.postValue(new ArrayList<>());
    }


    @Override
    public Conversation getConversation(String conversationID) {
        List<Conversation> aux = conversations.getValue();
        for(Conversation c : aux){
            if(c.id().equals(conversationID)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Conversation> getAllConversations() {
        List<Conversation> res ;
        DatabaseFactory.getDependency().fetchAll(collectionReference).
                continueWith((Continuation<CollectionSnapshot,Void>) task ->{
                    if (task.isSuccessful()){
                        CollectionSnapshot collection = task.getResult();
                        List<DocumentSnapshot> docs = collection.getDocuments();
                        List<Conversation> conv = new ArrayList<>();
                        for (DocumentSnapshot doc : docs){
                            conv.add(doc.toModel(Conversation.class));
                        }
                        conversations.setValue(conv);

                    }
                    else {
                        List<Conversation> x = new ArrayList<>();
                        conversations.setValue(new ArrayList<>());
                    }
                    return null;
                });


        res = conversations.getValue();
        List<Conversation> res2 = new ArrayList<>();
        for (Conversation c: res){

            /*
            if (!c.getUsers().contains(AuthenticatorFactory.getDependency().getCurrentUser())){
                res.remove(c);
            }
                         */
            for (User u : c.getUsers()){
                if (u.getId().equals("-1")){
                    res2.add(c);
                }
            }
        }
        conversations.postValue(res);
        return res;
    }


    @Override
    public void addConversation(Conversation conv) {
        if (conversations.getValue().add(conv)) {
            conversations.postValue(conversations.getValue());
        }
    }

    @Override
    public void updateConversation(Conversation conv, String conversationID) {
        List<Conversation> aux = conversations.getValue();
        for(Conversation c : aux){
            if(c.id().equals(conversationID)){
                aux.remove(c);
                aux.add(conv);
                conversations.postValue(aux);
                DatabaseFactory.getDependency().set(collectionReference,conversationID,conv);
                return;
            }
        }
    }

    @Override
    public void removeConversation(String conversationID) {

    }
}
