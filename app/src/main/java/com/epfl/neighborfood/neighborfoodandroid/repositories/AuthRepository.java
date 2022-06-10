package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorSingleton;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseSingleton;
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

    /**
     * Constructor of the auth repository
     */
    public AuthRepository() {
        //When the authentication status of the user changes,
        AuthenticatorSingleton.getDependency().addAuthStateChangeListener(() -> {
            if (AuthenticatorSingleton.getDependency().getCurrentAuthUser() != null) {
                // we add listeners to the database so that everytime the user is changed on the database,
                // we have the most "fresh" version of the user
                DatabaseSingleton.getDependency()
                        .addChangesListener(userCollection, AuthenticatorSingleton.getDependency().getCurrentAuthUser().getId(),
                                newModel -> user = newModel.toModel(User.class));
                // and we also fetch the currently authenticated user (if any) so that we have the user right away
                DatabaseSingleton.getDependency()
                        .fetch(userCollection, AuthenticatorSingleton.getDependency().getCurrentAuthUser().getId())
                        .addOnSuccessListener(fetchedUser -> user = fetchedUser.toModel(User.class));
            }
        });
    }

    /**
     * gets the authenticated user from the authenticator system
     *
     * @return the authenticator user, null if there's no authenticated user
     */
    public AuthenticatorUser getAuthUser() {
        return AuthenticatorSingleton.getDependency().getCurrentAuthUser();
    }

    /**
     * gets the currently authenticated user from the application
     *
     * @return the user, null if no user is authenticated
     */
    public User getCurrentUser() {
        return user;
    }

    /**
     * requests the authenticator to log out the current usser
     */
    public void logOut() {
        AuthenticatorSingleton.getDependency().logOut();
    }


    /**
     * Requests the authenticator to log in with a google account
     *
     * @param googleSignInAccount : the google account that was signed in
     * @return the task that may complete, with the authenticated user
     */
    public Task<AuthenticatorUser> logInWithGoogleAccount(GoogleSignInAccount googleSignInAccount) {
        return AuthenticatorSingleton.getDependency().logInWithGoogleAccount(googleSignInAccount)
                .continueWith(task -> AuthenticatorSingleton.getDependency().getCurrentAuthUser());
    }


}
