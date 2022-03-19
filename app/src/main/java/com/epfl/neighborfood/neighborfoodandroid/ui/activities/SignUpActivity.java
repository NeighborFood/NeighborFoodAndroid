package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epfl.neighborfood.neighborfoodandroid.login.Account;
import com.epfl.neighborfood.neighborfoodandroid.login.LoggedInUser;
import com.epfl.neighborfood.neighborfoodandroid.login.LoginModel;
import com.epfl.neighborfood.neighborfoodandroid.login.googleLogin.GoogleAccount;
import com.epfl.neighborfood.neighborfoodandroid.login.googleLogin.GoogleLoginModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MealListFragment;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseUser;
import com.epfl.neighborfood.neighborfoodandroid.R;

public class SignUpActivity extends AppCompatActivity {

    private SignInButton signInButton;
    private Button signOutButton;
    private Button startButton;
    private TextView guideTextView;
    private LoginModel loginModel;
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
        FirebaseUser currentUser = loginModel.getFirebaseLogin().getCurrentUser();
        LoggedInUser loggedInUser = currentUser != null ? new LoggedInUser(currentUser) : null;
        updateUI(loggedInUser);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Account account = loginModel.getLoginHandler().handleOnLoginIntentResult(requestCode, data);
        loginModel.getFirebaseLogin().loginWithCredential(((GoogleAccount)account).getAccountCredential(),this);

    }
    private void signIn(){
        startActivityForResult(loginModel.signIn(), RC_SIGN_IN);
    }

    public void signOut(){
        loginModel.signOut();
        updateUI(null);
    }

    public void updateUI(LoggedInUser loggedInUser){
        if(loggedInUser != null) {
            signOutButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            guideTextView.setText("Welcome: "+ loggedInUser.toString()+". Click start to discover the daily meals");


        } else {
            signOutButton.setVisibility(View.INVISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            guideTextView.setText("Please connect via your google account!");
        }
    }

    public void startScrolling(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
