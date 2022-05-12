package com.epfl.neighborfood.neighborfoodandroid.database;


/**
 * @author Mohamed Yassine Boukhari
 */
public class DatabaseFactory {

    private static Database dependency;


    /**
     * Gets the authenticator instance currently used
     * @return authenticator
     */
    public static Database getDependency() {
        return dependency;
    }

    /**
     * Sets the authenticator instance to be used
     */
    public static void setDependency(Database dep) {
        dependency = dep;
    }
}
