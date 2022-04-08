package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;

public abstract class  AppContainer {
    private AuthRepository authRepo;
    public AuthRepository getAuthRepo() {
        return authRepo;
    }
    protected AppContainer(AuthRepository authRepo){
        this.authRepo = authRepo;
    }

}
