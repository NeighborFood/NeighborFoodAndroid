package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.authentication.FirebaseAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.firebase.FirebaseDatabase;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.FirebaseNotificationService;

public class AppContainerImplementation extends AppContainer {
    protected AppContainerImplementation() {
        super(new AuthRepository(), new MealRepository(), new UserRepository(), new OrderRepository(), FirebaseDatabase.getInstance(), FirebaseAuthenticator.getInstance(), new FirebaseNotificationService());
    }
}
