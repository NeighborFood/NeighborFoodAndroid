package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.PlaceMealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.PlaceMealViewModelFactory;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity where vendors can place their meals, by uploading meal picture, selecting date, choosing allergens, and writing other details.
 */
public class PlaceMealActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, LocationListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    Map<ImageView, String> allergensIcons;
    Button confirmationButton;
    ImageButton addImageButton, calendarButton;
    List<String> allergensInMeal, allergens;
    EditText descriptionText, priceText, mealNameText, dateText, timeText;
    Toolbar toolbar;
    Uri image;
    Location location;
    TextView lon;
    TextView lat;
    LocationManager locationManager;
    private PlaceMealViewModel vmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_meal_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageToUpload = findViewById(R.id.imageToUpload);
        confirmationButton = findViewById(R.id.ConfirmationButton);
        addImageButton = findViewById(R.id.addPictureButton);
        vmodel = new ViewModelProvider(this, new PlaceMealViewModelFactory((NeighborFoodApplication) this.getApplication())).get(PlaceMealViewModel.class);

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

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
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
                //@TODO for sprint 9 I (Raed) will change the upload picture to be abstract and not deprecated
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.ConfirmationButton:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //TODO: replace with actual value of the place Meal
                Task<Void> task = vmodel.placeMeal(new Meal(mealNameText.getText().toString(), descriptionText.getText().toString() , descriptionText.getText().toString() , 0, new ArrayList<>(), 0, location));
                task.addOnCompleteListener((a)->{startActivity(i);});
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

    @Override
    public void onLocationChanged(@NonNull Location loc) {
        location = loc;
    }
}