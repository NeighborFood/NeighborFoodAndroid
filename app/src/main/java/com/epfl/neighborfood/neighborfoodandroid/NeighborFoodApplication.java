package com.epfl.neighborfood.neighborfoodandroid;

import android.app.Application;
import android.content.Context;

/**
 * NeighborFood Application class: attributes
 */
public class NeighborFoodApplication extends Application {
    public static AppContainer appContainer;

    public AppContainer getAppContainer(){
        if(appContainer == null){
            appContainer = new AppContainerImplementation();
        }
        return appContainer;
    }

    /** Getter for the app Context
     * @return the app context
     */
    public static Context getAppContext(){
        return getAppContext();
    }
}
