package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import android.annotation.SuppressLint;

import com.epfl.neighborfood.neighborfoodandroid.database.DBData;
import com.epfl.neighborfood.neighborfoodandroid.database.DBDataCollection;
import com.epfl.neighborfood.neighborfoodandroid.database.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.database.TentativeDB;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FirebaseDB implements TentativeDB {

    private static final String TAG = "FirebaseDB";
    private static FirebaseDB instance;


    private static final FirebaseFirestore database = FirebaseFirestore.getInstance();


    public static  FirebaseDB getInstance() {
        if (instance== null) {
            instance = new FirebaseDB();
        }
        return instance;
    }


    @Override
    public Task<DBData> fetch(String collectionPath, String documentPath) {
        return database.collection(collectionPath).document(documentPath)
                .get().onSuccessTask((documentSnapshot ->
                        Tasks.forResult(new FirebaseData(documentSnapshot))
                ));
    }

    @Override
    public Task<Void> set(String collectionPath, String documentPath, Map<String, Object> data) {
        return database.collection(collectionPath).document(documentPath)
                .set(data);
    }

    @Override
    public Task<Void> delete(String collectionPath, String documentPath) {
        return database.collection(collectionPath).document(documentPath).delete();
    }

    @Override
    public Task<String> add(String collectionPath, Map<String, Object> data) {
        return database.collection(collectionPath)
                .add(data).onSuccessTask((documentReference ->
                        Tasks.forResult(documentReference.getId())
                ));
    }

    @Override
    public Task<DBDataCollection> fetchAll(String collectionPath) {
        return null;
    }
}
