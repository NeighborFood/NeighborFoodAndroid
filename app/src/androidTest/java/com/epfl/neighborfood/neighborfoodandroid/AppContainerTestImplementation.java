package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.services.notification.LocationServiceTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.services.notification.NotificationServiceTestImplementation;

public class AppContainerTestImplementation extends AppContainer{
    public AppContainerTestImplementation() {
        super(null,DummyDatabase.getInstance(), DummyAuthenticator.getInstance(), new NotificationServiceTestImplementation(), new LocationServiceTestImplementation());
        setAuthRepo(new AuthRepositoryTestImplementation());
        setMealRepo(new MealRepositoryTestImplementation());
        setUserRepo(new UserRepositoryTestImplementation());
    }
}
