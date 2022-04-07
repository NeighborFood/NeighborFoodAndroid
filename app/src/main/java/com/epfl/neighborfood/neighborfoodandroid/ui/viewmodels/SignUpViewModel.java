package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpViewModel extends ViewModel {
    private AuthRepository authRepo;
    private LiveData<User> user;
    public SignUpViewModel(AuthRepository authRepo){
        this.authRepo = authRepo;
    }
    public LiveData<User> getCurrentUser (){
        if(user == null){
            user = authRepo.getUserLiveData();
        }
        return user;
    }
    public void signOut(){
        authRepo.logOut();
    }

    public void handleGoogleLoginResponse(int resultCode, Intent data){
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
            if (googleSignInAccount != null) {
                getGoogleAuthCredential(googleSignInAccount);
            }
        } catch (ApiException e) {
            System.out.println(e);

        }
    }
    private void getGoogleAuthCredential(GoogleSignInAccount googleSignInAccount) {
        String googleTokenId = googleSignInAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null);
        authRepo.authenticateWithCredential(googleAuthCredential);
    }
}
