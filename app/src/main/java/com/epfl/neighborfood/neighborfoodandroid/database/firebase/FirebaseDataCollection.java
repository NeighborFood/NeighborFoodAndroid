package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import com.epfl.neighborfood.neighborfoodandroid.database.DBData;
import com.epfl.neighborfood.neighborfoodandroid.database.DBDataCollection;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataCollection implements DBDataCollection {

    private QuerySnapshot collectionSnapshot;

    public FirebaseDataCollection(QuerySnapshot collectionSnapshot) {
        this.collectionSnapshot = collectionSnapshot;
    }

    @Override
    public List<DBData> getDocuments() {
        List<DBData> list = new ArrayList<>();

        for (DocumentSnapshot doc : collectionSnapshot.getDocuments()) {
            list.add(new FirebaseData(doc));
        }
        return list;
    }
}
