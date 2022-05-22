package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityProfileEditingBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.EditProfileViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.EditProfileViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.util.ImageUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class ProfileEditingActivity extends AppCompatActivity {
    private ActivityProfileEditingBinding binding;
    private EditProfileViewModel vmodel;
    private LinearLayout linksLayout;
    private ArrayList<TextInputEditText> textEdits = new ArrayList<>();
    @VisibleForTesting
    public static final String KEY_IMAGE_DATA = "data";
    private ImageView ppView;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private boolean photoChanged;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoChanged = false;
        binding = ActivityProfileEditingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        vmodel = new ViewModelProvider(this, new EditProfileViewModelFactory((NeighborFoodApplication) this.getApplication())).get(EditProfileViewModel.class);
        vmodel.loadCurrentUser()
                .addOnSuccessListener(this, this::updateUserFields)
                .addOnFailureListener(this,e-> Toast.makeText(this, R.string.load_failure, Toast.LENGTH_SHORT).show());
        setContentView(R.layout.activity_profile_editing);
        Toolbar toolbar = findViewById(R.id.profileEditToolbar);
        setSupportActionBar(toolbar);
        ppView = findViewById(R.id.profilePictureImageView);
        ppView.setOnClickListener(this::onClick);
        findViewById(R.id.profileEditAddLinkButton).setOnClickListener(this::onClick);
        linksLayout = findViewById(R.id.profileEditLinksLayout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        activityResultLauncher = ImageUtil.getImagePickerActivityLauncher(this,result -> {
            activityResult(result.getResultCode(), result.getData());
        });
    }

    /**
     * Updates the views with the given user
     *
     * @param user : the user containing the fields to update
     */
    private void updateUserFields(User user) {
        if (user == null) {
            return;
        }
        ((TextView) findViewById(R.id.nameValue)).setText(user.getFirstName());
        ((TextView) findViewById(R.id.surnameValue)).setText(user.getLastName());
        ((TextView) findViewById(R.id.emailValue)).setText(user.getEmail());
        ((TextView) findViewById(R.id.bioValue)).setText(user.getBio());
        ((TextView) findViewById(R.id.usernameValue)).setText(user.getUsername());
        Picasso.get().load(Uri.parse(user.getProfilePictureURI())).fit().into(ppView);
        updateUserLinks(user);

    }

    /**
     * Updates the view links list to reflect the attributes of the given user
     *
     * @param user : the user whose links we want to show
     */
    private void updateUserLinks(User user) {
        //discard all previous TextInputEditText
        linksLayout.removeAllViews();
        //add the links the user already has
        textEdits = new ArrayList<>();
        for (String s : user.getLinks()) {
            TextInputEditText t = addLinkInput();
            t.setText(s);
        }
        //add the empty link so the user can directly add a link
        addLinkInput();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profilePictureImageView:
                activityResultLauncher.launch(ImageUtil.getGalleryIntent());
                break;
            case R.id.saveButton:
                Task<Void> updateTask = photoChanged ? vmodel.updateUser(getUserWithUpdatedData(),filePath) : vmodel.updateUser(getUserWithUpdatedData()) ;

                updateTask.addOnSuccessListener((a)-> {
                            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener((a)->Toast.makeText(this, R.string.save_failure, Toast.LENGTH_SHORT).show());

            case R.id.profileEditAddLinkButton:
                TextInputEditText empty = addLinkInput();
                empty.requestFocus();

                break;
            default:
                break;
        }
    }

    /**
     * Reads the view values containing the fields to update in the user model
     *
     * @return an instance of the user with fields updated from the views
     */
    private User getUserWithUpdatedData() {
        //TODO: create User builder and replace this
        User currUser = vmodel.getCurrentUser();
        if (currUser == null) {
            return null;
        }
        String bio = ((TextInputEditText) findViewById(R.id.bioValue)).getEditableText().toString();
        User newUser = new User(currUser.getId(), currUser.getEmail(), currUser.getFirstName(), currUser.getLastName(),currUser.getProfilePictureURI().toString());
        newUser.setBio(bio);
        ArrayList<String> links = new ArrayList<>();
        for (TextInputEditText view : textEdits) {
            String text = view.getEditableText().toString();
            if (!text.equals("")) {
                links.add(text);
            }
        }

        newUser.setLinks(links);
        return newUser;
    }

    private void activityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            filePath = ImageUtil.getRealPathFromUri(data.getData(), this);
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                ppView.setImageBitmap(imageBitmap);
                photoChanged = true;
            } catch (IOException e) {
                Toast.makeText(this, R.string.image_load_error, Toast.LENGTH_SHORT).show();
            }
        }
    }



    /**
     * @return Adds a link input to the links list view
     */
    private TextInputEditText addLinkInput() {
        TextInputEditText empty = new TextInputEditText(this);
        linksLayout.addView(empty);
        empty.setHint("External Link");
        textEdits.add(empty);
        return empty;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // tool bar Back Icon
        {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
