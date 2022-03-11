package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class PlaceMealActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    Button confirmationButton;
    ImageButton addImageButton;
    EditText allergensText, descriptionText, priceText,mealNameText;
    String allergens,description, price, mealName;
    Toolbar toolbar;
    Uri mealImage,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_meal_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageToUpload = findViewById(R.id.imageToUpload);
        confirmationButton = findViewById(R.id.ConfirmationButton);
        addImageButton = findViewById(R.id.addPictureButton);
        allergensText = findViewById(R.id.textAllergens);
        descriptionText = findViewById(R.id.textDesciption);
        priceText = findViewById(R.id.textPrice);
        mealNameText = findViewById(R.id.textMealName);

       addImageButton.setOnClickListener(this);
       confirmationButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addPictureButton:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                break;
            case R.id.ConfirmationButton:
                Toast.makeText(this, "Congrats! Your meal has been uploaded", Toast.LENGTH_LONG).show();
                allergens= String.valueOf(allergensText.getText());
                description= String.valueOf(descriptionText.getText());
                price= String.valueOf(priceText.getText());
                mealName= String.valueOf(mealNameText.getText());
                mealImage = image;
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== RESULT_LOAD_IMAGE && resultCode ==RESULT_OK && data!= null){
            image = data.getData();
            imageToUpload.setImageURI(image);
        }

    }
}