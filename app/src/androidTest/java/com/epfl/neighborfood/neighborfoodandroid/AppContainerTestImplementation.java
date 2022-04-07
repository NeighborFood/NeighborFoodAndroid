package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryTestImplementation;

public class AppContainerTestImplementation extends AppContainer{
    public AppContainerTestImplementation() {
        super(new AuthRepositoryTestImplementation());
    }
}
