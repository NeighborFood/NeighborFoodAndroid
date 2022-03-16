package com.epfl.neighborfood.neighborfoodandroid.database;


public class DatabaseFactory {

    private static Database dependency = DummyDatabase.getInstance();


    /**
     * Gets the authenticator instance currently used
     * @return authenticator
     */
    public static Database getDependency() {
        return dependency;
    }

    /**
     * Sets the authenticator instance to be used
     * @param authenticator authenticator
     */
    public static void setDependency(Database authenticator) {
        dependency = authenticator;
    }
}
