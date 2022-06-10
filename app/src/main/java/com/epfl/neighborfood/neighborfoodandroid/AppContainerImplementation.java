package com.epfl.neighborfood.neighborfoodandroid;

import android.content.Context;

import com.epfl.neighborfood.neighborfoodandroid.authentication.FirebaseAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.firebase.FirebaseDatabase;
import com.epfl.neighborfood.neighborfoodandroid.services.location.GoogleLocationService;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.FirebaseNotificationService;

/**
 * The production container of the application
 */
public class AppContainerImplementation extends AppContainer {
    protected AppContainerImplementation(Context context) {
        super(context, FirebaseDatabase.getInstance(), FirebaseAuthenticator.getInstance(), new FirebaseNotificationService(), new GoogleLocationService());
    }
}
