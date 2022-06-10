package com.epfl.neighborfood.neighborfoodandroid.database;

import java.util.List;

/**
 * A collection of query results
 *
 * @author Mohamed Yassine Boukhari
 */

public interface CollectionSnapshot {
    /**
     * @return the query results in a list
     */
    List<DocumentSnapshot> getDocuments();
}
