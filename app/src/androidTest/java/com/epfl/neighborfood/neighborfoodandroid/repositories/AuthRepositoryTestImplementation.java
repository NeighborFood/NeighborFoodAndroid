package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class AuthRepositoryTestImplementation extends AuthRepository{
    private final AuthenticatorUser authUser = new AuthenticatorUser("-1","","","","") {
    };
    private User user = new User("-1","","","","");
    @Override
    public Task<AuthenticatorUser> logInWithGoogleAccount(GoogleSignInAccount account) {
        DummyAuthenticator.getInstance().logInWithGoogleAccount(account);
        return Tasks.forResult(authUser);
    }

    /** Sets directly the currently authenticated user
     * @param user : the user to put as the logged in user
     */
    public void setUser(User user){
        DummyAuthenticator.getInstance().setAuthUser(new AuthenticatorUser(user.getId(),user.getEmail(),user.getFirstName(),user.getLastName(),user.getProfilePictureURI()) {
        });
        this.user =user;
    }
    @Override
    public User getCurrentUser(){
        return this.user;
    }


}
