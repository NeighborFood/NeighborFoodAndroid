package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import android.app.Activity;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleAccountConfigurator {

    // the activity that will handle the login feature
    private Activity signUpActivity;
    // the signIn google client (in the context of the login activity)
    private GoogleSignInClient mGoogleSignInClient;
    public GoogleAccountConfigurator(Activity signUpActivity){
        this.signUpActivity = signUpActivity;
        activateGoogleSignIn();
    }

    /**
     * This function builds the google sign in options and use it to recover the google sign in client
     */
    private void activateGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(signUpActivity.getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(signUpActivity, gso);
    }

    /**
     * @return the google signIn client of the login activity
     */
    public GoogleSignInClient getGoogleSignInClient() {
        return mGoogleSignInClient;
    }
}
