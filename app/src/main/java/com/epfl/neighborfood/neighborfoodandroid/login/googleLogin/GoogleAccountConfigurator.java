package com.epfl.neighborfood.neighborfoodandroid.login.googleLogin;

import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleAccountConfigurator {

    // the activity that will handle the login feature
    private SignUpActivity signUpActivity;
    // the signIn google client (in the context of the login activity)
    private GoogleSignInClient mGoogleSignInClient;
    private static String serverId = "743325402792-a1337fr4929vml06r8pkrtaasubvpusp.apps.googleusercontent.com";

    public GoogleAccountConfigurator(SignUpActivity signUpActivity){
        this.signUpActivity = signUpActivity;
        activateGoogleSignIn();
    }

    /**
     * This function builds the google sign in options and use it to recover the google sign in client
     */
    private void activateGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverId)
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
