package com.epfl.neighborfood.neighborfoodandroid.database.firebase;

import com.epfl.neighborfood.neighborfoodandroid.database.DBData;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirebaseData implements DBData {

    private DocumentSnapshot documentSnapshot;
    

    public FirebaseData(DocumentSnapshot documentSnapshot) {
        this.documentSnapshot = documentSnapshot;
    }


    @Override
    public Object get(String field) {
        return documentSnapshot.get(field);
    }
}
