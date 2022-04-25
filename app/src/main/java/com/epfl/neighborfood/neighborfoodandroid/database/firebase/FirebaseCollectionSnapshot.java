package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseCollectionSnapshot implements CollectionSnapshot {

    private QuerySnapshot collectionSnapshot;

    public FirebaseCollectionSnapshot(QuerySnapshot collectionSnapshot) {
        this.collectionSnapshot = collectionSnapshot;
    }

    @Override
    public List<DocumentSnapshot> getDocuments() {
        List<DocumentSnapshot> list = new ArrayList<>();

        for (com.google.firebase.firestore.DocumentSnapshot doc : collectionSnapshot.getDocuments()) {
            list.add(new FirebaseDocumentSnapshot(doc));
        }
        return list;
    }
}
