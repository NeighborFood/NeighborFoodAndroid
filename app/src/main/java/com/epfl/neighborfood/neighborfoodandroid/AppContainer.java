package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;

public abstract class  AppContainer {
    private AuthRepository authRepo;
    public AuthRepository getAuthRepo() {
        return authRepo;
    }
    protected AppContainer(AuthRepository authRepo, Database dep){
        this.authRepo = authRepo;
        DatabaseFactory.setDependency(dep);
    }

}
