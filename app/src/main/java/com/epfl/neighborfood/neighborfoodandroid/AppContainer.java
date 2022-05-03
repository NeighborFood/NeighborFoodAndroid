package com.epfl.neighborfood.neighborfoodandroid;

import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;

/**
 * The access point for all the dependencies of the app
 */
public abstract class AppContainer {
    private AuthRepository authRepo;
    private MealRepository mealRepo;
    private UserRepository userRepo;
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

    /**
     * @param authRepo the app's authentication repository
     * @param mealRepo the app's meal repository
     * @param mealRepo the app's user repository
     * @param dep      the app's database
     */
    protected AppContainer(AuthRepository authRepo, MealRepository mealRepo, UserRepository userRepo, Database dep) {
        this.authRepo = authRepo;
        this.userRepo = userRepo;
        this.mealRepo = mealRepo;
        DatabaseFactory.setDependency(dep);
    }

}
