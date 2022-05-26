package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.assertEquals;

import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.google.android.gms.tasks.Task;

import org.junit.Test;

import java.util.List;

public class DatabaseFactoryTest {

    @Test
    public void dependencyTest() {

        Database dep = new Database() {
            @Override
            public Task<DocumentSnapshot> fetch(String collectionPath, String documentPath) {
                return null;
            }

            @Override
            public Task<Void> set(String collectionPath, String documentPath, Object data) {
                return null;
            }

            @Override
            public Task<Void> delete(String collectionPath, String documentPath) {
                return null;
            }

            @Override
            public Task<String> add(String collectionPath, Object data) {
                return null;
            }

            @Override
            public Task<CollectionSnapshot> fetchAll(String collectionPath) {
                return null;
            }

            @Override
            public void addChangesListener(String collectionPath, String collectionId, ModelUpdateListener listener) {

            }

            @Override
            public Task<List<DocumentSnapshot>> fetchAllArrayAttributeContains(String collectionPath, String attributeName, String attributeValue) {
                return null;
            }
        };

        DatabaseFactory.setDependency(dep);
        assertEquals(dep,DatabaseFactory.getDependency());
    }
}
