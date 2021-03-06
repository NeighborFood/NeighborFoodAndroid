package com.epfl.neighborfood.neighborfoodandroid.database;

import com.google.android.gms.tasks.Task;

import java.util.List;

/**
 * @author Mohamed Yassine Boukhari
 */
public interface Database {
    interface ModelUpdateListener {
        void onModelUpdate(DocumentSnapshot newModel);
    }

    /**
     * Fetches the requested data
     *
     * @param collectionPath collection
     * @param documentPath   document id
     * @return task that fails if the database is unreachable
     */
    Task<DocumentSnapshot> fetch(String collectionPath, String documentPath);

    /**
     * Writes the requested data. The data will be written at the requested path, even if it already exists
     *
     * @param collectionPath collection
     * @param documentPath   document id
     * @param data           data to write
     * @return task that fails if the database is unreachable
     */
    Task<Void> set(String collectionPath, String documentPath, Object data);

    /**
     * Deletes the requested data
     *
     * @param collectionPath collection
     * @param documentPath   document id
     * @return task that fails if the database is unreachable
     */
    Task<Void> delete(String collectionPath, String documentPath);

    /**
     * Adds the requested data in the database. The id will be chosen automatically
     *
     * @param data data to write
     * @return task that fails if the database is unreachable
     */
    Task<String> add(String collectionPath, Object data);

    /**
     * Fetches all the data in a collection
     *
     * @param collectionPath collection
     * @return task that fails if the database is unreachable
     */
    Task<CollectionSnapshot> fetchAll(String collectionPath);

    /**
     * Fetches all the data in a collection that corresponds to an attribute value.
     *
     * @param collectionPath collection
     * @param attributeName  attribute type
     * @param attributeValue value of the attribute
     * @return task that fails if the database is unreachable
     */
    Task<CollectionSnapshot> fetchAllMatchingAttributeValue(String collectionPath, String attributeName, Object attributeValue);

    void addChangesListener(String collectionPath, String collectionId, ModelUpdateListener listener);

    Task<List<DocumentSnapshot>> fetchAllArrayAttributeContains(String collectionPath, String attributeName, String attributeValue);

}
