package com.epfl.neighborfood.neighborfoodandroid.database;

import java.util.List;

public interface CollectionSnapshot {
    List<DocumentSnapshot> getDocuments();
}
