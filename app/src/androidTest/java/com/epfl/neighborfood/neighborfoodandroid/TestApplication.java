package com.epfl.neighborfood.neighborfoodandroid;

public class TestApplication extends NeighborFoodApplication {
    public AppContainer getAppContainer(){
        if(appContainer == null){
            appContainer = new AppContainerTestImplementation();
        }
        return appContainer;
    }
}
