package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabase implements Database {

    private static final String TAG = "FirebaseDB";
    private static FirebaseDatabase instance;


    private static FirebaseFirestore database = FirebaseFirestore.getInstance();


    private FirebaseDatabase() {
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
        return database.collection(collectionPath).get().onSuccessTask(
                (collectionSnapshot ->
                Tasks.forResult(new FirebaseCollectionSnapshot(collectionSnapshot))
        ));
    }

    @Override
    public Task<CollectionSnapshot> fetchAllMatchingAttributeValue(String collectionPath,String attributeName, Object attributeValue) {
        return database.collection(collectionPath).whereEqualTo(attributeName,attributeValue).get().onSuccessTask(
                (collectionSnapshot ->
                        Tasks.forResult(new FirebaseCollectionSnapshot(collectionSnapshot))
                ));
    }


    public void addChangesListener(String collectionPath, String documentPath, ModelUpdateListener listener) {
        database.collection(collectionPath).document(documentPath).addSnapshotListener(
                (value, error) -> listener.onModelUpdate(new FirebaseDocumentSnapshot(value))
        );
    }

    @Override
    public Task<List<DocumentSnapshot>> fetchAllArrayAttributeContains(String collectionPath, String attributeName, String attributeValue) {
        return database.collection(collectionPath).whereArrayContains(attributeName,attributeValue).get().continueWith(t->{
            List<com.google.firebase.firestore.DocumentSnapshot> ds = t.getResult().getDocuments();
            List<DocumentSnapshot> res = new ArrayList<>();
            for (com.google.firebase.firestore.DocumentSnapshot doc: ds) {
                res.add(new FirebaseDocumentSnapshot(doc));
            }

            return res;
        });
    }
}
