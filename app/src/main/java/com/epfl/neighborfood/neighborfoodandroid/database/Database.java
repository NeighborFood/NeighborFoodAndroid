package com.epfl.neighborfood.neighborfoodandroid.database;

import com.epfl.neighborfood.neighborfoodandroid.models.Model;
import com.google.android.gms.tasks.Task;

/**
 * @author Mohamed Yassine Boukhari
 */
public interface Database {
    interface ModelUpdateListener{
        void onModelUpdate(DocumentSnapshot newModel);
    }

    /**
     * Fetches the requested data
     * @param collectionPath collection
     * @param documentPath document id
     * @return task that fails if the database is unreachable
     */
    Task<DocumentSnapshot> fetch(String collectionPath, String documentPath);

    /**
     * Writes the requested data. The data will be written at the requested path, even if it already exists
     * @param collectionPath collection
     * @param documentPath document id
     * @param data data to write
     * @return task that fails if the database is unreachable
     */
    Task<Void> set(String collectionPath, String documentPath, Object data);

    /**
     * Deletes the requested data
     * @param collectionPath collection
     * @param documentPath document id
     * @return task that fails if the database is unreachable
     */
    Task<Void> delete(String collectionPath, String documentPath);

    /**
     * Adds the requested data in the database. The id will be chosen automatically
     * @param data data to write
     * @return task that fails if the database is unreachable
     */
    Task<String> add(String collectionPath, Object data);

    /**
     * Fetches all the data in a collection
     * @param collectionPath collection
     * @return task that fails if the database is unreachable
     */
    Task<CollectionSnapshot> fetchAll(String collectionPath);

    void addChangesListener(String collectionPath, String collectionId, ModelUpdateListener listener);

}
