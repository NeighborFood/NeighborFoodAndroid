package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.AuthenticatorUser;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.SignUpViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.SignupActivityViewModelFactory;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

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
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private GoogleSignInClient googleSignInClient;
    private boolean newUser;


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
        signOutButton.setOnClickListener(v -> signOut());
    }

    private void initSignInButton() {
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> signIn());
    }

    private void initAuthViewModel() {

        viewModel = new ViewModelProvider(this, new SignupActivityViewModelFactory((NeighborFoodApplication) getApplication())).get(SignUpViewModel.class);
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    activityResult(result.getResultCode(), result.getData());
                });
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }



    public void activityResult(int resultCode, Intent data) {
        viewModel.handleGoogleLoginResponse(resultCode, data).addOnSuccessListener(newUser->{
            this.newUser = newUser;
            updateUI(viewModel.getCurrentAuthUser());
        }).addOnFailureListener(e->{
            Toast.makeText(this, R.string.login_failure, Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
        } );
    }


    /**
     * Log in into the app using the loginModel intent
     */
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();

        activityResultLauncher.launch(signInIntent);
    }

    /**
     * Log out from the app by shutting off(and signing out) the service responsible for the log in feature
     */
    public void signOut() {
        viewModel.signOut();
        updateUI(viewModel.getCurrentAuthUser());

    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI(viewModel.getCurrentAuthUser());

    }

    /**
     * Updates the UI according to whether there is a logged in user or not
     *
     * @param user: the current logged in user
     */
    public void updateUI(AuthenticatorUser user) {
        if (user != null) {
            signOutButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            if(newUser){
                guideTextView.setText(getResources().getString(R.string.welcome_message, user.getFirstName()));
                startButton.setText(R.string.finish_profile);
            }else{
                guideTextView.setText(getResources().getString(R.string.welcome_back_message, user.getFirstName()));
                startButton.setText(R.string.start);
            }


        } else {
            signOutButton.setVisibility(View.INVISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            guideTextView.setText(R.string.connection_invitation);
        }
    }


    /**
     * the onClick handler of the start button which will allow the user to go the meal activity
     */
    public void startScrolling(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
