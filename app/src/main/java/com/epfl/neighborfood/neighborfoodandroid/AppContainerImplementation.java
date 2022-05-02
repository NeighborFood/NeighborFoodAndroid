package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.database.firebase.FirebaseDatabase;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;

public class AppContainerImplementation extends AppContainer{
    protected AppContainerImplementation() {
        super(new AuthRepository(), FirebaseDatabase.getInstance());
    }
}
