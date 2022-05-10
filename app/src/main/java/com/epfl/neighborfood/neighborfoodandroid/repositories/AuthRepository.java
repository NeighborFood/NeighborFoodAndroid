package com.epfl.neighborfood.neighborfoodandroid.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.epfl.neighborfood.neighborfoodandroid.authentication.Authenticator;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

/**
 * The entry point to the authentication repository
 */
public class AuthRepository {
    private final static String userCollection = "Users";
    private User user;
    public AuthRepository() {
        AuthenticatorFactory.getDependency().addAuthStateChangeListener(() -> {
            if(AuthenticatorFactory.getDependency().getCurrentAuthUser() != null){
                DatabaseFactory.getDependency()
                        .addChangesListener(userCollection, AuthenticatorFactory.getDependency().getCurrentAuthUser().getId(),
                                newModel -> user = newModel.toModel(User.class));
                DatabaseFactory.getDependency()
                        .fetch(userCollection,AuthenticatorFactory.getDependency().getCurrentAuthUser().getId())
                        .addOnSuccessListener(fetchedUser->user = fetchedUser.toModel(User.class));
            }
        });
    }
    public AuthenticatorUser getAuthUser(){
        return AuthenticatorFactory.getDependency().getCurrentAuthUser();
    }

    public User getCurrentUser(){
        return user;
    }

    /**
     * requests the authenticator to log out the current usser
     */
    public void logOut() {
        AuthenticatorFactory.getDependency().logOut();
    }


    /**
     * Requests the authenticator to log in with a google account
     *
     * @param googleSignInAccount : the google account that was signed in
     * @return the task that may complete, with the authenticated user
     */
    public Task<AuthenticatorUser> logInWithGoogleAccount(GoogleSignInAccount googleSignInAccount) {
        return AuthenticatorFactory.getDependency().logInWithGoogleAccount(googleSignInAccount)
                .continueWith(task-> AuthenticatorFactory.getDependency().getCurrentAuthUser());
    }


}
