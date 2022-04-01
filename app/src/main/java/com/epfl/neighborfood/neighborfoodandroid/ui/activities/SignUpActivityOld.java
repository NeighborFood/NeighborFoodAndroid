package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epfl.neighborfood.neighborfoodandroid.login.Account;
import com.epfl.neighborfood.neighborfoodandroid.models.UserFirebaseImpl;
import com.epfl.neighborfood.neighborfoodandroid.login.LoginModel;
import com.epfl.neighborfood.neighborfoodandroid.login.googleLogin.GoogleAccount;
import com.epfl.neighborfood.neighborfoodandroid.login.googleLogin.GoogleLoginModel;
import com.google.android.gms.common.SignInButton;
import com.epfl.neighborfood.neighborfoodandroid.R;

public class SignUpActivityOld extends AppCompatActivity {

    // the button used to log in
    private SignInButton signInButton;
    // the button used to log out
    private Button signOutButton;
    // the button used to start using the other features of the app
    private Button startButton;
    // a guiding text view, telling the user what to do next
    private TextView guideTextView;
    // a login model allowing signing up/signing out from the app
    private LoginModel loginModel;
    // a request code for the sign in intent
    int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginModel = new GoogleLoginModel(this);

        signInButton = findViewById(R.id.sign_in_button);
        signOutButton = findViewById(R.id.sign_out_button);
        startButton = findViewById(R.id.start_button);
        guideTextView = findViewById(R.id.guide_textView);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        signOutButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        }));
    }

    @Override
    public void onStart() {
        super.onStart();
        com.google.firebase.auth.FirebaseUser currentUser = loginModel.getFirebaseLogin().getCurrentUser();
        UserFirebaseImpl userFirebaseImpl = currentUser != null ? new UserFirebaseImpl(currentUser) : null;
        updateUI(userFirebaseImpl);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Account account = loginModel.getLoginHandler().handleOnLoginIntentResult(requestCode, data);
        loginModel.getFirebaseLogin().loginWithCredential(((GoogleAccount)account).getAccountCredential());

    }

    /**
     * Log in into the app using the loginModel intent
     */
    private void signIn(){
        startActivityForResult(loginModel.signIn(), RC_SIGN_IN);
    }

    /**
     * Log out from the app by shutting off(and signing out) the service responsible for the log in feature
     */
    public void signOut(){
        loginModel.signOut();
        updateUI(null);
    }

    /**
     * Updates the UI according to whether there is a logged in user or not
     * @param userFirebaseImpl: the current logged in user
     */
    public void updateUI(UserFirebaseImpl userFirebaseImpl){
        if(userFirebaseImpl != null) {
            signOutButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            guideTextView.setText("Welcome: "+ userFirebaseImpl.toString()+". Click start to discover the daily meals");


        } else {
            signOutButton.setVisibility(View.INVISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            guideTextView.setText("Please connect via your google account!");
        }
    }

    /**
     * the onClick handler of the start button which will allow the user to go the meal activity
     * @param view(View)
     */
    public void startScrolling(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
