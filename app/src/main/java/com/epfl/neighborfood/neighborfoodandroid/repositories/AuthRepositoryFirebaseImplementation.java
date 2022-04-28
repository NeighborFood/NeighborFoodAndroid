package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.authentication.FirebaseAuthenticator;

public class AuthRepositoryFirebaseImplementation extends AuthRepository {
    public AuthRepositoryFirebaseImplementation() {
        super(new FirebaseAuthenticator());
        authenticator = new FirebaseAuthenticator();
    }

}
