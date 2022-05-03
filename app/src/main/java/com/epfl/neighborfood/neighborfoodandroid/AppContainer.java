package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService;

/**
 * The access point for all the dependencies of the app
 */
public abstract class AppContainer {
    private AuthRepository authRepo;
    private MealRepository mealRepo;
    private UserRepository userRepo;
    private NotificationService notificationService;
    /**
     * getter for the Auth Repo of the app
     *
     * @return the authentication repository of the app
     */
    public AuthRepository getAuthRepo() {
        return authRepo;
    }
    /**
     * getter for the Meal Repo of the app
     *
     * @return the meal repository of the app
     */
    public MealRepository getMealRepo() {
        return mealRepo;
    }
    /**
     * getter for the User Repo of the app
     *
     * @return the user repository of the app
     */
    public UserRepository getUserRepo() {
        return userRepo;
    }

    /** getter for the notification service of the app
     * @return  the notification service
     */
    public NotificationService getNotificationService(){
        return notificationService;
    }

    /**
     * @param authRepo the app's authentication repository
     * @param mealRepo the app's meal repository
     * @param mealRepo the app's user repository
     * @param dep      the app's database
     */
    protected AppContainer(AuthRepository authRepo, MealRepository mealRepo, UserRepository userRepo, Database dep, NotificationService notificationService) {
        this.authRepo = authRepo;
        this.userRepo = userRepo;
        this.mealRepo = mealRepo;
        this.notificationService = notificationService;
        DatabaseFactory.setDependency(dep);
    }

}
