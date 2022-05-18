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
    private static Map<String, DocumentSnapshot> users;
    private static Map<String, DocumentSnapshot> meals;
    private final List<ModelUpdateListener> userUpdateListeners;

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

    }
    private void initializeFields(){
        users = new HashMap<>();
        User userwithFakeLinks= new User("-1","zbiba@epfl.ch","Zbiba","Zabboub","");
        ArrayList<String> fakeLinks =new ArrayList();
        fakeLinks.add("a");fakeLinks.add("b");fakeLinks.add("c");
        userwithFakeLinks.setLinks(fakeLinks);
        users.put("-1", new UserDocumentSnapshot(userwithFakeLinks));

        users.put("1", new UserDocumentSnapshot(new User("1","a","a","a","")));
        users.put("2", new UserDocumentSnapshot(new User("2","b","b","b","")));
        users.put("3", new UserDocumentSnapshot(new User("3","c","c","c","")));
        meals = new HashMap<>();
        int[] imageId = {R.drawable.poulet, R.drawable.couscous, R.drawable.paella,
                R.drawable.fondue, R.drawable.salade, R.drawable.soupe, R.drawable.tarte};
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

        for (int i = 0; i < imageId.length; i++) {
            Meal meal = new Meal(mealsName[i], mealsShortDes[i], mealsLongDes[i], "",new ArrayList<>(),0,new Date());
            meals.put(Integer.toString(i),new MealDocumentSnapshot(meal));
        }
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
                if (chatter.getId().equals(conv.getChatter().getId())){
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
        int uid = -1;
        if(collectionPath.equals("Users")) {
            uid = users.size()+1;
        }
        if(collectionPath.equals("Meals")){
            uid = meals.size()+1;
        }
        int finalUid = uid;
        return set(collectionPath,Integer.toString(uid),data).continueWith(task -> Integer.toString(finalUid));
    }

    @Override
    public Task<CollectionSnapshot> fetchAll(String collectionPath) {
        switch (collectionPath){
            case "Meals":
                System.out.println(meals.values().iterator().next().toModel(Meal.class).getMealId());
                return Tasks.forResult(new CollectionSnapshotImpl(meals.values()));
        }
        return null;
    }

    @Override
    public void addChangesListener(String collectionPath, String collectionId, ModelUpdateListener listener) {
        if(collectionPath.equals("Users")){
            userUpdateListeners.add(listener);
        }
    }
}
