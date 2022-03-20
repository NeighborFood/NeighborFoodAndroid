package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleAccountConfigurator {

    private SignUpActivity signUpActivity;
    private GoogleSignInClient mGoogleSignInClient;

    public GoogleAccountConfigurator(SignUpActivity signUpActivity){
        this.signUpActivity = signUpActivity;
        activateGoogleSignIn();
    }

    private void activateGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("743325402792-a1337fr4929vml06r8pkrtaasubvpusp.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(signUpActivity, gso);
    }

    public GoogleSignInClient getGoogleSignInClient() {
        return mGoogleSignInClient;
    }
}
