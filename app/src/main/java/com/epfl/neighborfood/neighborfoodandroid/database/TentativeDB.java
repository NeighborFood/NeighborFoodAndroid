package com.epfl.neighborfood.neighborfoodandroid.database;

import com.epfl.neighborfood.neighborfoodandroid.database.firebase.FirebaseDB;
import com.google.android.gms.tasks.Task;

import java.util.Map;

public interface TentativeDB {


    /**
     * Fetches the requested data
     * @param collectionPath collection
     * @param documentPath document id
     * @return task that fails if the database is unreachable
     */
    Task<DBData> fetch(String collectionPath, String documentPath);

    /**
     * Writes the requested data. The data will be written at the requested path, even if it already exists
     * @param collectionPath collection
     * @param documentPath document id
     * @param data data to write
     * @return task that fails if the database is unreachable
     */
    Task<Void> set(String collectionPath, String documentPath, Map<String, Object> data);

    /**
     * Deletes the requested data
     * @param collectionPath collection
     * @param documentPath document id
     * @return task that fails if the database is unreachable
     */
    Task<Void> delete(String collectionPath, String documentPath);

    /**
     * Adds the requested data in the database. The id will be chosen automatically
     * @param collectionPath collection
     * @param data data to write
     * @return task that fails if the database is unreachable
     */
    Task<String> add(String collectionPath, Map<String, Object> data);

    /**
     * Fetches all the data in a collection
     * @param collectionPath collection
     * @return task that fails if the database is unreachable
     */
    Task<DBDataCollection> fetchAll(String collectionPath);


}
