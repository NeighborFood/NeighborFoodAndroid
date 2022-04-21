package com.epfl.neighborfood.neighborfoodandroid.database;

import static org.junit.Assert.assertEquals;

import com.google.android.gms.tasks.Task;

import org.junit.Test;

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
        };

        DatabaseFactory.setDependency(dep);
        assertEquals(dep,DatabaseFactory.getDependency());
    }
}
