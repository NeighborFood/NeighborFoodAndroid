package com.epfl.neighborfood.neighborfoodandroid.database.cache;

import static java.util.stream.Collectors.toList;

import android.annotation.SuppressLint;

import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Model;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author Mohamed Yassine Boukhari
 */
public class CacheDatabase implements Database{

    private static CacheDatabase instance;
    private final static String USERS_COLLECTION_PATH = "Users";
    private final static String MEALS_COLLECTION_PATH = "Meals";
    private final static String CONV_COLLECTION_PATH = "Conversations";
    private static Map<String, User> users;
    private static Map<String, Meal> meals;
    private static Map<String, Conversation> conversations;
    private final List<Database.ModelUpdateListener> userUpdateListeners;


    private class UserDocumentSnapshot extends User implements DocumentSnapshot{
        private final User user;
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
        private final Meal meal;
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
            return null;
        }
    }

    private class CollectionSnapshotImpl implements CollectionSnapshot{
        private final List<DocumentSnapshot> documents;
        @SuppressLint("NewApi")
        CollectionSnapshotImpl(Collection<DocumentSnapshot> documents){
            this.documents = documents.stream().collect(toList());
        }
        @Override
        public List<DocumentSnapshot> getDocuments() {
            return documents;
        }
    }

    public CacheDatabase(){
        initializeFields();

        try {
            FileInputStream fis1 = new FileInputStream("Users.ser");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            users = (Map<String,User>) ois1.readObject();
            ois1.close();

            FileInputStream fis2 = new FileInputStream("Meals.ser");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            meals = (Map<String,Meal>) ois2.readObject();
            ois2.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        userUpdateListeners = new ArrayList<>();
    }

    private void initializeFields(){
        users = new HashMap<>();
        meals = new HashMap<>();
        conversations = new HashMap<>();
    }



    public static CacheDatabase getInstance() {
        if (instance == null) {
            instance = new CacheDatabase();
        }
        return instance;
    }


    public void reset() {
        initializeFields();
    }

    public void persist() throws Exception{
        FileOutputStream fos1 = new FileOutputStream("Users.ser");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        oos1.writeObject(users);
        oos1.close();

        FileOutputStream fos2 = new FileOutputStream("Meals.ser");
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        oos1.writeObject(meals);
        oos2.close();
    }


    @Override
    public Task<DocumentSnapshot> fetch(String collectionPath, String documentPath) {
        if(collectionPath.equals(USERS_COLLECTION_PATH) ){
            if(users.containsKey(documentPath)) {
                DocumentSnapshot ds = new UserDocumentSnapshot(users.get(documentPath));
                return Tasks.forResult(ds);
            }

            return DatabaseFactory.getDependency().fetch(collectionPath, documentPath).continueWith(
                    task -> {
                        if (task.isSuccessful()){
                            DocumentSnapshot ds = task.getResult();
                            User u = ds.toModel(User.class);
                            users.put(documentPath,u);
                            return ds;
                        }
                        return null;
                    }
            );
        }
        else if (collectionPath.equals(MEALS_COLLECTION_PATH)){
            if(meals.containsKey(documentPath)){
                DocumentSnapshot ds = new MealDocumentSnapshot(meals.get(documentPath));
                return Tasks.forResult(ds);
            }
            return DatabaseFactory.getDependency().fetch(collectionPath, documentPath).continueWith(
                    task -> {
                        if (task.isSuccessful()){
                            DocumentSnapshot ds = task.getResult();
                            Meal u = ds.toModel(Meal.class);
                            meals.put(documentPath,u);
                            return ds;
                        }
                        return null;
                    }
            );
        }
        return DatabaseFactory.getDependency().fetch(collectionPath, documentPath);
    }

    @Override
    public Task<Void> set(String collectionPath, String documentPath, Object data) {
        if(collectionPath.equals(USERS_COLLECTION_PATH) ){
            if(users.containsKey(documentPath)) {
                users.put(documentPath,(User)data);
            }
        }
        else if (collectionPath.equals(MEALS_COLLECTION_PATH)){
            if(meals.containsKey(documentPath)){
                meals.put(documentPath,(Meal)data);
            }
        }
        return DatabaseFactory.getDependency().set(collectionPath,documentPath,data);
    }

    @Override
    public Task<Void> delete(String collectionPath, String documentPath) {
        if(collectionPath.equals(USERS_COLLECTION_PATH) ){
            users.remove(documentPath);
        }
        else if (collectionPath.equals(MEALS_COLLECTION_PATH)){
            meals.remove(documentPath);
        }
        return DatabaseFactory.getDependency().delete(collectionPath,documentPath);
    }

    @Override
    public Task<String> add(String collectionPath, Object data) {
        switch (collectionPath) {
            case USERS_COLLECTION_PATH:
                return DatabaseFactory.getDependency().add(collectionPath, data).continueWith(
                        task -> {
                            if (task.isSuccessful()) {
                                users.put(task.getResult(), (User) data);
                                return task.getResult();
                            }
                            return null;
                        }
                );
            case MEALS_COLLECTION_PATH:
                return DatabaseFactory.getDependency().add(collectionPath, data).continueWith(
                        task -> {
                            if (task.isSuccessful()) {
                                meals.put(task.getResult(), (Meal) data);
                                return task.getResult();
                            }
                            return null;
                        }
                );
            case CONV_COLLECTION_PATH:
                return DatabaseFactory.getDependency().add(collectionPath, data).continueWith(
                        task -> {
                            if (task.isSuccessful()) {
                                conversations.put(task.getResult(), (Conversation) data);
                                return task.getResult();
                            }
                            return null;
                        }
                );
            default:
                return DatabaseFactory.getDependency().add(collectionPath, data);
        }
    }

    @Override
    public Task<CollectionSnapshot> fetchAll(String collectionPath) {
            return DatabaseFactory.getDependency().fetchAll(collectionPath);
    }

    @Override
    public Task<CollectionSnapshot> fetchAllMatchingAttributeValue(String collectionPath, String attributeName, Object attributeValue) {
        return null;
    }

    @Override
    public void addChangesListener(String collectionPath, String collectionId, Database.ModelUpdateListener listener) {
        if(collectionPath.equals("Users")){
            userUpdateListeners.add(listener);
        }
    }

    @Override
    public Task<List<DocumentSnapshot>> fetchAllArrayAttributeContains(String collectionPath, String attributeName, String attributeValue) {
        return null;
    }


}
