package com.epfl.neighborfood.neighborfoodandroid.database.dummy;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.Model;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class DummyDatabase implements Database {

    private final List<Message> chatDB = new ArrayList<>();
    private final ArrayList<Conversation> conversationsDB = new ArrayList<>();
    private static DummyDatabase instance;
    public static final int PROFILE_IMG_ID = R.drawable.profile_img_male;
    private static Map<String, DocumentSnapshot> users;

    private class UserDocumentSnapshot extends User implements DocumentSnapshot{
        private User user;
        public UserDocumentSnapshot(User user){
        }
        @Override
        public Object get(String field) {
            return null;
        }

        @Override
        public <T extends Model> T toModel(Class<T> clazz) {
            if(clazz == this.getClass()){
                return null;
            }
            return (T)user;
        }
    }
    private DummyDatabase(){
        users = new HashMap<>();
        users.put("1", new UserDocumentSnapshot(new User("1","a","a","a")));
        users.put("2", new UserDocumentSnapshot(new User("2","b","b","b")));
        users.put("3", new UserDocumentSnapshot(new User("3","c","c","c")));

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
        if(collectionPath.equals("Users")){
            if(users.containsKey(documentPath)){
                return Tasks.forResult(users.get(documentPath));
            }else{
                return Tasks.forException(new NoSuchElementException("User not found"));
            }
        }
        return null;
    }

    @Override
    public Task<Void> set(String collectionPath, String documentPath, Object data) {
        if(collectionPath.equals("Users") ){
            if(! (data instanceof User)){
                return Tasks.forException(new IllegalArgumentException("Cannot save data to collection"));
            }
            users.put(documentPath,new UserDocumentSnapshot((User) data));
            return Tasks.forResult(null);
        }
        return null;
    }

    @Override
    public Task<Void> delete(String collectionPath, String documentPath) {
        if(collectionPath.equals("Users") ){
            if(users.containsKey(documentPath)){
                return Tasks.forResult(null);
            }else{
                return Tasks.forException(new NoSuchElementException("User not found"));
            }
        }
        return null;
    }

    @Override
    public Task<String> add(String collectionPath, Object data) {
        if(collectionPath == "User") {
            int uid = users.size();
            return set(collectionPath,Integer.toString(uid),data).continueWith(task -> Integer.toString(uid));
        }
        return null;
    }

    @Override
    public Task<CollectionSnapshot> fetchAll(String collectionPath) {

        return null;
    }
}
