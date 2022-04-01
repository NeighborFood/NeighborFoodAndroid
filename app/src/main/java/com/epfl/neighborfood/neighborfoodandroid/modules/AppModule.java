package com.epfl.neighborfood.neighborfoodandroid.modules;

import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthAppRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Singleton
    @Provides
    public AuthAppRepository provideAuthRepository() {
        return new AuthAppRepository();
    }
}
