package com.epfl.neighborfood.neighborfoodandroid;

import android.content.Context;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.utils.ObjectUtils;
import com.epfl.neighborfood.neighborfoodandroid.authentication.Authenticator;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.ConversationRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.LocationService;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * The access point for all the dependencies of the app
 */
public abstract class AppContainer {
    private ConversationRepository conversationRepo;
    private AuthRepository authRepo;
    private MealRepository mealRepo;
    private UserRepository userRepo;
    private OrderRepository orderRepo;
    private NotificationService notificationService;
    private LocationService locationService;
    /**
     * getter for the Auth Repo of the app
     *
     * @return the authentication repository of the app
     */
    public AuthRepository getAuthRepo() {
        return authRepo;
    }
    /**
     * getter for the Order Repo of the app
     *
     * @return the authentication repository of the app
     */
    public OrderRepository getOrderRepo() {
        return orderRepo;
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

    /** getter for the conversation repository
     * @return the conversation repository
     */
    public ConversationRepository getConversationRepo(){
        return this.conversationRepo;
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
    /** Setter for the conversation repository
     * @param conversationRepository the conversation repository to set
     *
     */
    protected void setConversationRepo(ConversationRepository conversationRepository){
        this.conversationRepo = conversationRepository;
    }
    public LocationService getLocationService(){
        return locationService;
    }
    protected void setLocationService(LocationService locationService){
        this.locationService = locationService;
    }
    /**
     * @param dep the app's Database instance
     * @param authenticator the app's authenticator
     * @param notificationService the app's notification service
     *
     */
    protected AppContainer(Context context, Database dep, Authenticator authenticator, NotificationService notificationService,LocationService locationService) {
        DatabaseFactory.setDependency(dep);
        AuthenticatorFactory.setDependency(authenticator);
        this.notificationService = notificationService;
        this.locationService = locationService;
        this.authRepo = new AuthRepository();
        this.userRepo = new UserRepository();
        this.mealRepo = new MealRepository();
        this.conversationRepo = new ConversationRepository();
        this.orderRepo = new OrderRepository();
        if(context == null){
            return;
        }
        Map config = ObjectUtils.asMap(
                "cloud_name", "dkg9lec21",
                "api_key", "778522893956734",
                "api_secret", "XHWHbaQg49cZsbk9QPvAV5PaXF8");
        Cloudinary cloudinary = new Cloudinary(config);
        MediaManager.init(context,config);

    }


}
