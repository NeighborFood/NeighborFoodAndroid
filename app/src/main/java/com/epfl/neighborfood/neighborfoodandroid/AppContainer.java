package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;

/**
 * The access point for all the dependencies of the app
 */
public abstract class AppContainer {
    private AuthRepository authRepo;

    /**
     * getter for the Auth Repo of the app
     *
     * @return the authentication repository of the app
     */
    public AuthRepository getAuthRepo() {
        return authRepo;
    }

    /**
     * @param authRepo the app's authentication repository
     * @param dep      the app's database
     */
    protected AppContainer(AuthRepository authRepo, Database dep) {
        this.authRepo = authRepo;
        DatabaseFactory.setDependency(dep);
    }

}
