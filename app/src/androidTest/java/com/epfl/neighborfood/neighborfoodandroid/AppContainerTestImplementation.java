package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.services.notification.NotificationServiceTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService;

public class AppContainerTestImplementation extends AppContainer{
    public AppContainerTestImplementation() {
        super(null,DummyDatabase.getInstance(), DummyAuthenticator.getInstance(), new NotificationServiceTestImplementation());
        setAuthRepo(new AuthRepositoryTestImplementation());
        setMealRepo(new MealRepositoryTestImplementation());
        setUserRepo(new UserRepositoryTestImplementation());
    }
}
