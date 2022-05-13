package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.authentication.Authenticator;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
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

    /**Setter for the auth repository
     * @param authRepo the authrepo to set
     */
    protected void setAuthRepo(AuthRepository authRepo){
        this.authRepo = authRepo;
    }

    /** Setter for the meal repository
     * @param mealRepo the meal repo to set
     */
    protected void setMealRepo(MealRepository mealRepo){
        this.mealRepo = mealRepo;
    }

    /**Setter for the user repository
     * @param userRepo the user repository
     */
    protected void setUserRepo(UserRepository userRepo){
        this.userRepo = userRepo;
    }
    /**
     * @param dep the app's Database instance
     * @param authenticator the app's authenticator
     * @param notificationService the app's notification service
     */
    protected AppContainer( Database dep, Authenticator authenticator, NotificationService notificationService) {
        DatabaseFactory.setDependency(dep);
        AuthenticatorFactory.setDependency(authenticator);
        this.notificationService = notificationService;
        this.authRepo = new AuthRepository();
        this.userRepo = new UserRepository();
        this.mealRepo = new MealRepository();

    }

}
