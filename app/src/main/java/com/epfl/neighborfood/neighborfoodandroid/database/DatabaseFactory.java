package com.epfl.neighborfood.neighborfoodandroid.database;


import com.epfl.neighborfood.neighborfoodandroid.database.firebase.FirebaseDB;

public class DatabaseFactory {

    private static TentativeDB dependency = FirebaseDB.getInstance();


    /**
     * Gets the authenticator instance currently used
     * @return authenticator
     */
    public static TentativeDB getDependency() {
        return dependency;
    }

    /**
     * Sets the authenticator instance to be used
     */
    public static void setDependency(TentativeDB dep) {
        dependency = dep;
    }
}
