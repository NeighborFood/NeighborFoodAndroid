package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import com.epfl.neighborfood.neighborfoodandroid.login.LoginModel;
import com.epfl.neighborfood.neighborfoodandroid.login.firebase.FirebaseLogin;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;


public class GoogleLoginModel extends LoginModel {

    public GoogleLoginModel(SignUpActivity signUpActivity) {
        super(new GoogleLogin(new GoogleAccountConfigurator(signUpActivity).getGoogleSignInClient()), new GoogleLoginHandler(), new FirebaseLogin());
    }
}
