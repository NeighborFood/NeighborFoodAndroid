package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryFirebaseImplementation;

public class AppContainerImplementation extends AppContainer{
    protected AppContainerImplementation() {
        super(new AuthRepositoryFirebaseImplementation());
    }
}
