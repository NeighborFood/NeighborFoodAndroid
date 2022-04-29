package com.epfl.neighborfood.neighborfoodandroid.database;

import com.epfl.neighborfood.neighborfoodandroid.models.Model;

/**
 * A database query result
 * @author Mohamed Yassine Boukhari
 */
public interface DocumentSnapshot {

    /**
     * fetches a specific field from the query result
     * @param field the name of the field to be fetched
     * @return
     */
    Object get(String field);


    /**
     * converts a query result to a model
     * @param clazz the class to convert the query result to
     * @param <T>
     * @return the model after conversion
     */
    <T extends Model> T toModel(Class<T> clazz);
}
