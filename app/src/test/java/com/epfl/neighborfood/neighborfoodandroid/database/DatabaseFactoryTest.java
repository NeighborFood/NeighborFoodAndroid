package com.epfl.neighborfood.neighborfoodandroid.database;

import static org.junit.Assert.assertEquals;

import com.epfl.neighborfood.neighborfoodandroid.database.DBData;
import com.epfl.neighborfood.neighborfoodandroid.database.DBDataCollection;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.TentativeDB;
import com.google.android.gms.tasks.Task;

import org.junit.Test;

import java.util.Map;

public class DatabaseFactoryTest {

    @Test
    public void dependencyTest() {

        TentativeDB dep = new TentativeDB() {
            @Override
            public Task<DBData> fetch(String collectionPath, String documentPath) {
                return null;
            }

            @Override
            public Task<Void> set(String collectionPath, String documentPath, Map<String, Object> data) {
                return null;
            }

            @Override
            public Task<Void> delete(String collectionPath, String documentPath) {
                return null;
            }

            @Override
            public Task<String> add(String collectionPath, Map<String, Object> data) {
                return null;
            }

            @Override
            public Task<DBDataCollection> fetchAll(String collectionPath) {
                return null;
            }
        };

        //DatabaseFactory.setDependency(dep);
        //assertEquals(dep,DatabaseFactory.getDependency());
    }
}
