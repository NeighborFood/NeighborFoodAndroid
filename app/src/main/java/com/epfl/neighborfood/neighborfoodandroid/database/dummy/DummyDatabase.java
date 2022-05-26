package com.epfl.neighborfood.neighborfoodandroid.database.dummy;

import static java.util.stream.Collectors.toList;

import android.annotation.SuppressLint;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.Model;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class DummyDatabase implements Database {

    private final List<Message> chatDB = new ArrayList<>();
    private final ArrayList<Conversation> conversationsDB = new ArrayList<>();
    private static DummyDatabase instance;
    public static final int PROFILE_IMG_ID = R.drawable.profile_img_male;
    private static Map<String, UserDocumentSnapshot> users;
    private static Map<String, MealDocumentSnapshot> meals;
    private static Map<String, ConversationDocumentSnapshot> conversations;
    private final List<ModelUpdateListener> userUpdateListeners;
    private final List<ModelUpdateListener> conversationsListeners;

    private class UserDocumentSnapshot extends User implements DocumentSnapshot{
        private User user;
        public UserDocumentSnapshot(User user){
            this.user = user;
        }
        @Override
        public Object get(String field) {
            switch (field){
                case User.KEY_FIRSTNAME:
                    return user.getFirstName();
                case User.KEY_LASTNAME:
                    return user.getLastName();
                case User.KEY_USERNAME:
                    return user.getUsername();
                case User.KEY_EMAIL:
                    return user.getEmail();
                case User.KEY_BIO:
                    return user.getBio();
                case User.KEY_ID:
                    return user.getId();
                case User.KEY_PP_URI:
                    return user.getProfilePictureURI();
                case User.KEY_LINKS:
                    return user.getLinks();
            }
            return null;
        }

        @Override
        public <T extends Model> T toModel(Class<T> clazz) {
            if(clazz != User.class){
                return null;
            }
            return (T)user;
        }
    }
    private class MealDocumentSnapshot extends Meal implements DocumentSnapshot{
        private Meal meal;
        public MealDocumentSnapshot(Meal meal){
            this.meal = meal;
        }
        @Override
        public Object get(String field) {
            return null;
        }

        @Override
        public <T extends Model> T toModel(Class<T> clazz) {
            if(clazz != Meal.class){
                return null;
            }
            return (T)meal;
        }

        @Override
        public String getId() {
            return meal.getMealId();
        }
    }
    private class CollectionSnapshotImpl implements CollectionSnapshot{
        private List<DocumentSnapshot> documents;
        @SuppressLint("NewApi")
        CollectionSnapshotImpl(Collection<DocumentSnapshot> documents){
            this.documents = documents.stream().collect(toList());
        }
        @Override
        public List<DocumentSnapshot> getDocuments() {
            return documents;
        }
    }

    private DummyDatabase(){
        initializeFields();
        userUpdateListeners = new ArrayList<>();

        conversationsListeners = new ArrayList<>();
    }
    private class ConversationDocumentSnapshot extends Conversation implements DocumentSnapshot{
        private Conversation convo;
        public ConversationDocumentSnapshot(Conversation convo){
            this.convo = convo;
        }
        @Override
        public Object get(String field) {
            return null;
        }

        @Override
        public <T extends Model> T toModel(Class<T> clazz) {
            if(clazz != Meal.class){
                return null;
            }
            return (T)convo;
        }

        @Override
        public String getId() {
            return convo.getId();
        }
    }
    private void initializeFields(){
        users = new HashMap<>();
        User userwithFakeLinks= new User("-1","zbiba@epfl.ch","Zbiba","Zabboub","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon);
        ArrayList<String> fakeLinks =new ArrayList();
        fakeLinks.add("https://facebook.com/");fakeLinks.add("https://twitter.com/");fakeLinks.add("a");
        userwithFakeLinks.setLinks(fakeLinks);
        users.put("-1", new UserDocumentSnapshot(userwithFakeLinks));
        users.put("0", new UserDocumentSnapshot(new User("0","z","z","z","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon)));

        users.put("1", new UserDocumentSnapshot(new User("1","a","a","a","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon)));
        users.put("2", new UserDocumentSnapshot(new User("2","b","b","b","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon)));
        users.put("3", new UserDocumentSnapshot(new User("3","c","c","c","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon)));
        meals = new HashMap<>();
       String[] mealsName = {"Poulet au miel",
                "Couscous aux légumes",
                "Paella aux crevettes",
                "Fondue Moitié-Moitié",
                "Salade légère",
                "Soupe à l'oignon",
                "Tarte aux pommes"};

        String[] mealsShortDes = {"Un délicieux poulet au miel",
                "Un couscous comme à la maison",
                "Une paella traditionnelle",
                "Une bonne fondue",
                "Une salade à la tomate",
                "De la soupe à l'oignon",
                "Une bonne tarte aux pommes"};

        String[] mealsLongDes = {"Vous ne pourrez pas résister à ce savoureux poulet",
                "Ce couscous me fait penser à celui que me faisait mon grand-père",
                "Recette de paella directement d'Italie !",
                "blabla fondue",
                "blabla salade",
                "blabla soupe",
                "blabla tarte"};

        for (int i = 0; i < mealsName.length; i++) {
            Meal meal = new Meal(mealsName[i], mealsShortDes[i], mealsLongDes[i], "android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon,new ArrayList<>(),0,new Date());
            meals.put(Integer.toString(i),new MealDocumentSnapshot(meal));
        }
        conversations = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add("0");
        ids.add("1");
        conversations.put("0-1",new ConversationDocumentSnapshot(new Conversation("0-1",new ArrayList<>(ids),new ArrayList<>())));
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
                if (chatter.getId().equals(conv.chatter(chatter.getId()))){
                    return conv.getMessages();
                }
            }

        }
        return new ArrayList<>();
    }


    public void reset() {
        initializeFields();
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
        if(collectionPath.equals("Conversations")){
            if(conversations.containsKey(documentPath)){
                return Tasks.forResult(conversations.get(documentPath));
            }else{
                return Tasks.forException(new NoSuchElementException("conversation not found"));
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
            UserDocumentSnapshot newSnapshot = new UserDocumentSnapshot((User) data);
            users.put(documentPath,newSnapshot);
            for (ModelUpdateListener l:
                 userUpdateListeners) {
                l.onModelUpdate(newSnapshot);
            }
            return Tasks.forResult(null);
        }
        if(collectionPath.equals("Conversations") ){
            if(! (data instanceof Conversation)){
                return Tasks.forException(new IllegalArgumentException("Cannot save data to collection"));
            }
            ConversationDocumentSnapshot newSnapshot = new ConversationDocumentSnapshot((Conversation) data);
            conversations.put(documentPath,newSnapshot);
            for (ModelUpdateListener l:
                    conversationsListeners) {
                l.onModelUpdate(newSnapshot);
            }
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
        String uid = "-1";
        if(collectionPath.equals("Users")) {
            uid = Integer.toString(users.size()+1);
        }
        if(collectionPath.equals("Meals")){
            uid = Integer.toString(meals.size()+1);
        }
        if(collectionPath.equals("Conversations")){
            uid = ((Conversation)data).getId();
        }
        String finalUid = uid;
        return set(collectionPath,uid,data).continueWith(task -> finalUid);
    }

    @Override
    public Task<CollectionSnapshot> fetchAll(String collectionPath) {
        switch (collectionPath){
            case "Meals":
                Collection<? extends DocumentSnapshot> bases = meals.values();
                return Tasks.forResult(new CollectionSnapshotImpl((Collection<DocumentSnapshot>) bases));
        }
        return null;
    }

    @Override
    public void addChangesListener(String collectionPath, String collectionId, ModelUpdateListener listener) {
        if(collectionPath.equals("Users")){
            userUpdateListeners.add(listener);
        }
        if(collectionPath.equals("Conversations")){
            conversationsListeners.add(listener);
        }
    }

    @Override
    public Task<List<DocumentSnapshot>> fetchAllArrayAttributeContains(String collectionPath, String attributeName, String attributeValue) {
        List<DocumentSnapshot> res = new ArrayList<>();
        if(collectionPath.equals("Conversations") && attributeName.equals("users")){
            for(ConversationDocumentSnapshot d : conversations.values()){
                if(d.getUsers().contains(attributeValue)){
                    res.add(d);
                }
            }
        }
        return Tasks.forResult(res);
    }
}
