package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.epfl.neighborfood.neighborfoodandroid.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceMealActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    Map<ImageView, String> allergensIcons;
    Button confirmationButton;
    ImageButton addImageButton, calendarButton;
    List<String> allergensInMeal, allergens;
    EditText descriptionText, priceText, mealNameText, dateText, timeText;
    Toolbar toolbar;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_meal_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageToUpload = findViewById(R.id.imageToUpload);
        confirmationButton = findViewById(R.id.ConfirmationButton);
        addImageButton = findViewById(R.id.addPictureButton);

        allergensInMeal = new ArrayList<String>();
        allergensIcons = new HashMap<ImageView, String>();
        allergensIcons.put(findViewById(R.id.CeleryIcon), "celery");
        allergensIcons.put(findViewById(R.id.MilkIcon), "milk");
        allergensIcons.put(findViewById(R.id.FishIcon), "fish");
        allergensIcons.put(findViewById(R.id.CheeseIcon), "cheese");
        allergensIcons.put(findViewById(R.id.GlutenIcon), "gluten");
        allergensIcons.put(findViewById(R.id.HoneyIcon), "honey");
        allergensIcons.put(findViewById(R.id.LobsterIcon), "Lobster");
        allergensIcons.put(findViewById(R.id.SoyIcon), "Soy");
        allergensIcons.put(findViewById(R.id.EggsIcon), "Eggs");
        allergensIcons.put(findViewById(R.id.ChocolateIcon), "Chocolate");

        descriptionText = findViewById(R.id.textDesciption);
        priceText = findViewById(R.id.textPrice);
        mealNameText = findViewById(R.id.textMealName);
        calendarButton = findViewById(R.id.CalendarButton);
        dateText = findViewById(R.id.DateText);
        timeText = findViewById(R.id.TimeText);
        //listeners
        for (ImageView icon : allergensIcons.keySet()) {
            icon.setOnClickListener(this);
        }
        calendarButton.setOnClickListener(this);
        addImageButton.setOnClickListener(this);
        confirmationButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (allergensIcons.keySet().contains(v)) {
            String allergenName = allergensIcons.get(v);
            if (allergensInMeal.contains(allergensIcons.get(v))) {
                allergensInMeal.remove(allergenName);
                v.setBackgroundColor(0xFFFFFF);
            } else {
                allergensInMeal.add(allergenName);
                v.setBackgroundColor(0x666BEC70);
            }
        }
        switch (v.getId()) {
            case R.id.addPictureButton:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.ConfirmationButton:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("meal_name", String.valueOf(mealNameText.getText()));
                i.putExtra("description", String.valueOf(descriptionText.getText()));
                i.putExtra("price", String.valueOf(priceText.getText()));
                i.putExtra("time", String.valueOf(priceText.getText()));
                i.putExtra("date", String.valueOf(dateText.getText()));
                i.putExtra("allergens", new ArrayList<>(allergensInMeal));
                //***********************
                //HERE the image should be put in extra for intent too
                //mealImage = image;
                //***********************
                startActivity(i);
                break;
            case R.id.CalendarButton:
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this,
                        this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            image = data.getData();
            imageToUpload.setImageURI(image);
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateText.setText(dayOfMonth + "/" + (month + 1) + "/" + year, TextView.BufferType.EDITABLE);
    }
}