package com.epfl.neighborfood.neighborfoodandroid.database;

import com.epfl.neighborfood.neighborfoodandroid.models.Model;

public interface DocumentSnapshot {
    Object get(String field);
    <T extends Model> T toModel(Class<T> clazz);
}
