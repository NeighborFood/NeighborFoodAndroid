package com.epfl.neighborfood.neighborfoodandroid;

import android.app.Application;
import android.content.Context;

import com.cloudinary.android.MediaManager;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * NeighborFood Application class: attributes
 */
public class NeighborFoodApplication extends Application {
    public static AppContainer appContainer;


    public AppContainer getAppContainer(){
        if(appContainer == null){
            appContainer = new AppContainerImplementation(getApplicationContext());
        }
        return appContainer;
    }
}
