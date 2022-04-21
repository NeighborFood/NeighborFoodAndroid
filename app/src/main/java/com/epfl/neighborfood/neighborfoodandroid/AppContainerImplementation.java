package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.database.firebase.FirebaseDatabase;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryFirebaseImplementation;

public class AppContainerImplementation extends AppContainer{
    protected AppContainerImplementation() {
        super(new AuthRepositoryFirebaseImplementation(), FirebaseDatabase.getInstance());
    }
}
