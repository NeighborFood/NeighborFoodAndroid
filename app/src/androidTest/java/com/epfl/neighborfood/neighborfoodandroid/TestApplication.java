package com.epfl.neighborfood.neighborfoodandroid;

import android.app.Application;

public class TestApplication extends NeighborFoodApplication {
    public AppContainer getAppContainer(){
        if(appContainer == null){
            appContainer = new AppContainerTestImplementation();
        }
        return appContainer;
    }
}
