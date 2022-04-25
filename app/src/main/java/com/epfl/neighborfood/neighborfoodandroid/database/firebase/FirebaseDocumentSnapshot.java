package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Model;

public class FirebaseDocumentSnapshot implements DocumentSnapshot {

    private com.google.firebase.firestore.DocumentSnapshot documentSnapshot;
    

    public FirebaseDocumentSnapshot(com.google.firebase.firestore.DocumentSnapshot documentSnapshot) {
        this.documentSnapshot = documentSnapshot;
    }


    @Override
    public Object get(String field) {
        return documentSnapshot.get(field);
    }

    @Override
    public <T extends Model> T toModel(Class<T> clazz) {
        return documentSnapshot.toObject(clazz);
    }


}
