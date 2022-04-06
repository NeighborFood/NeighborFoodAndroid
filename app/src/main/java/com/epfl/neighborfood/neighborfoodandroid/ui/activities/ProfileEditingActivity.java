package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epfl.neighborfood.neighborfoodandroid.AppContainer;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityProfileEditingBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.EditProfileViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.EditProfileViewModelFactory;
import com.squareup.picasso.Picasso;


public class ProfileEditingActivity extends AppCompatActivity {
    private ActivityProfileEditingBinding binding;
    private EditProfileViewModel vmodel;
    @VisibleForTesting
    public static final String KEY_IMAGE_DATA = "data";
    private ImageView ppView;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                activityResult(result.getResultCode(),result.getData());
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileEditingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        vmodel = new ViewModelProvider(this, new EditProfileViewModelFactory((NeighborFoodApplication) this.getApplication())).get(EditProfileViewModel.class);
        vmodel.getCurrentUser().observe(this,user -> {
            updateUserFields(user);
        });
        setContentView(R.layout.activity_profile_editing);
        Toolbar toolbar = findViewById(R.id.profileEditToolbar);
        setSupportActionBar(toolbar);

        ppView = findViewById(R.id.profilePictureImageView);
        ppView.setOnClickListener(this::onClick);

    }

    public void updateUserFields(User user) {
        if(user != null){
            ((TextView)findViewById(R.id.nameValue)).setText(user.getFirstName());
            ((TextView)findViewById(R.id.surnameValue)).setText(user.getLastName());
            ((TextView)findViewById(R.id.emailValue)).setText(user.getEmail());
            Picasso.with(this).load(user.getProfilePictureURI()).fit().into(ppView);
        }

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.profilePictureImageView:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(galleryIntent,1);
                activityResultLauncher.launch(galleryIntent);
                break;
            case R.id.saveButton:
                vmodel.saveUserData();
                Toast.makeText(this, "Successfully Saved!", Toast.LENGTH_SHORT).show();
                finish();
            default:
                break;
        }
    }
    private void activityResult(int resultCode, Intent data) {
        if (resultCode ==RESULT_OK && data!= null){
            Bundle extras = data.getExtras();
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap imageBitmap = (Bitmap) extras.get(KEY_IMAGE_DATA);
            ppView.setImageBitmap(imageBitmap);
        }

    }
}
