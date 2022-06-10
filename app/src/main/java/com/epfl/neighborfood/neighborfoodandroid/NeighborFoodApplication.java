package com.epfl.neighborfood.neighborfoodandroid;

import android.app.Application;

/**
 * NeighborFood Application class: attributes
 */
public class NeighborFoodApplication extends Application {
    public static AppContainer appContainer;
    private static Context context;

    public void onCreate(){
        super.onCreate();
        NeighborFoodApplication.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return NeighborFoodApplication.context;
    }

    public AppContainer getAppContainer(){
        if(appContainer == null){
            appContainer = new AppContainerImplementation(getApplicationContext());
        }
        return appContainer;
    }
}
