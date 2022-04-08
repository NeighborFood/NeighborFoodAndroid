package com.epfl.neighborfood.neighborfoodandroid.modules;

import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryTestImplementation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;

@Module
@TestInstallIn(components = SingletonComponent.class,replaces = AppModule.class )
public class TestAppModule {
    @Singleton
    @Provides
    public AuthRepository provideAuthRepository() {
        return new AuthRepositoryTestImplementation();
    }
}
