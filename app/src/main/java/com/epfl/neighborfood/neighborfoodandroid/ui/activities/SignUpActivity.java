package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.EditProfileViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.SignUpViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.EditProfileViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.SignupActivityViewModelFactory;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.epfl.neighborfood.neighborfoodandroid.R;

public class SignUpActivity extends AppCompatActivity {
    private SignUpViewModel viewModel;
    // the button used to log in
    private SignInButton signInButton;
    // the button used to log out
    private Button signOutButton;
    // the button used to start using the other features of the app
    private Button startButton;
    // a guiding text view, telling the user what to do next
    private TextView guideTextView;

    private GoogleSignInClient googleSignInClient;


    // a request code for the sign in intent
    @VisibleForTesting
    public final static int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        startButton = findViewById(R.id.start_button);
        guideTextView = findViewById(R.id.guide_textView);



        initSignInButton();
        initSignOutButton();
        initAuthViewModel();
        initGoogleSignInClient();
    }

    private void initSignOutButton() {
        signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(v->signOut());
    }

    private void initSignInButton() {
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> signIn());
    }

    private void initAuthViewModel() {

        viewModel = new ViewModelProvider(this, new SignupActivityViewModelFactory((NeighborFoodApplication) getApplication())).get(SignUpViewModel.class);
        viewModel.getCurrentUser().observe(this,(user->updateUI(user)));
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI(viewModel.getCurrentUser().getValue());

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            viewModel.handleGoogleLoginResponse(resultCode, data);
        }

    }


    /**
     * Log in into the app using the loginModel intent
     */
    private void signIn(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * Log out from the app by shutting off(and signing out) the service responsible for the log in feature
     */
    public void signOut(){
        viewModel.signOut();

    }
    /**
     * Updates the UI according to whether there is a logged in user or not
     * @param user: the current logged in user
     */
    public void updateUI(User user){
        if(user != null) {
            signOutButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            guideTextView.setText("Welcome: "+ user.getFullName()+". Click start to discover the daily meals");


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
