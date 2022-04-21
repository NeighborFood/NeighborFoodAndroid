package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDatabase implements Database {

    private static final String TAG = "FirebaseDB";
    private static FirebaseDatabase instance;


    private static FirebaseFirestore database = FirebaseFirestore.getInstance();


    public FirebaseDatabase() {
    }

    public static FirebaseDatabase getInstance() {
        if (instance== null) {
            instance = new FirebaseDatabase();
        }
        return instance;
    }


    @Override
    public Task<DocumentSnapshot> fetch(String collectionPath, String documentPath) {
        return database.collection(collectionPath).document(documentPath)
                .get().onSuccessTask((documentSnapshot ->
                        Tasks.forResult(new FirebaseDocumentSnapshot(documentSnapshot))
                ));
    }

    @Override
    public Task<Void> set(String collectionPath, String documentPath, Object data) {
        return database.collection(collectionPath).document(documentPath)
                .set(data);
    }

    @Override
    public Task<Void> delete(String collectionPath, String documentPath) {
        return database.collection(collectionPath).document(documentPath).delete();
    }

    @Override
    public Task<String> add(String collectionPath, Object data) {
        return database.collection(collectionPath)
                .add(data).onSuccessTask((documentReference ->
                        Tasks.forResult(documentReference.getId())
                ));
    }

    @Override
    public Task<CollectionSnapshot> fetchAll(String collectionPath) {
        return null;
    }
}
