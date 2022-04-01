package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import android.app.Activity;

import com.epfl.neighborfood.neighborfoodandroid.login.LoginModel;
import com.epfl.neighborfood.neighborfoodandroid.login.firebase.FirebaseLogin;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;


public class GoogleLoginModel extends LoginModel {

    /**
     * Set up the google sign in feature for the parameter activity in order to have a GoogleLogin
     * and creates a GoogleLoginHandler / a FirebaseLogin allowing the execution of signUp/signOut actions.
     * @param signUpActivity(SignUpActivity): the activity responsible of the log in feature
     */
    public GoogleLoginModel(Activity signUpActivity) {
        super(new GoogleLogin(new GoogleAccountConfigurator(signUpActivity).getGoogleSignInClient()), new GoogleLoginHandler(), new FirebaseLogin());
    }
}
