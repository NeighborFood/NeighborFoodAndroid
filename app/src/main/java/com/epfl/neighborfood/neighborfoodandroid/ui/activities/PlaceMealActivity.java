package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.PlaceMealViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.util.ImageUtil;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity where vendors can place their meals, by uploading meal picture, selecting date, choosing allergens, and writing other details.
 */
public class PlaceMealActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    @SuppressLint("SimpleDateFormat")
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    ImageView imageToUpload;
    Map<ImageView, Allergen> allergensIcons;
    Button confirmationButton, mapButton;
    ImageButton addImageButton, calendarButton;
    EditText descriptionText, priceText, mealNameText, dateText, timeText;
    List<Allergen> allergensInMeal;
    Toolbar toolbar;
    Uri image;
    PickupLocation location;
    private String imagePath;
    private PlaceMealViewModel vmodel;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ActivityResultLauncher<Intent> locationActivityResultLauncher;


    private List<EditText> cannotBeEmptyFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_meal_menu);
        toolbar = findViewById(R.id.PlaceMealToolbar);
        setSupportActionBar(toolbar);
        imageToUpload = findViewById(R.id.imageToUpload);
        confirmationButton = findViewById(R.id.ConfirmationButton);
        mapButton = findViewById(R.id.locationButton);
        addImageButton = findViewById(R.id.addPictureButton);
        vmodel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) this.getApplication())).get(PlaceMealViewModel.class);

        allergensInMeal = new ArrayList<>();
        allergensIcons = new HashMap<>();
        allergensIcons.put(findViewById(R.id.CeleryIcon), Allergen.CELERY);
        allergensIcons.put(findViewById(R.id.MilkIcon), Allergen.MILK);
        allergensIcons.put(findViewById(R.id.FishIcon), Allergen.FISH);
        allergensIcons.put(findViewById(R.id.CheeseIcon), Allergen.CHEESE);
        allergensIcons.put(findViewById(R.id.GlutenIcon), Allergen.GLUTEN);
        allergensIcons.put(findViewById(R.id.HoneyIcon), Allergen.HONEY);
        allergensIcons.put(findViewById(R.id.LobsterIcon), Allergen.LOBSTER);
        allergensIcons.put(findViewById(R.id.SoyIcon), Allergen.SOY);
        allergensIcons.put(findViewById(R.id.EggsIcon), Allergen.EGGS);
        allergensIcons.put(findViewById(R.id.ChocolateIcon), Allergen.CHOCOLATE);
        descriptionText = findViewById(R.id.textDescription);
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
        mapButton.setOnClickListener(this);

        // This is to create a list that need to not be empty and be checked for it
        cannotBeEmptyFields = new ArrayList<>();
        cannotBeEmptyFields.add(descriptionText);
        cannotBeEmptyFields.add(priceText);
        cannotBeEmptyFields.add(mealNameText);
        cannotBeEmptyFields.add(dateText);
        cannotBeEmptyFields.add(timeText);

        activityResultLauncher = ImageUtil.getImagePickerActivityLauncher(this, result -> imageActivityResult(result.getResultCode(), result.getData()));

        locationActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        double chosenLon = result.getData().getDoubleExtra("longitude",0);
                        double chosenLat = result.getData().getDoubleExtra("latitude",0);
                        location = new PickupLocation(chosenLat,chosenLon);
                    }
                }
        );
    }

    private void imageActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            imagePath = ImageUtil.getRealPathFromUri(data.getData(), this);
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                imageToUpload.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                Toast.makeText(this, R.string.image_load_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (allergensIcons.containsKey(v)) {
            Allergen allergen = allergensIcons.get(v);
            if (allergensInMeal.contains(allergen)) {
                allergensInMeal.remove(allergen);
                v.setBackgroundColor(0xFFFFFF);
            } else {
                allergensInMeal.add(allergen);
                v.setBackgroundColor(0x666BEC70);
            }
        }
        switch (v.getId()) {
            case R.id.addPictureButton:
                activityResultLauncher.launch(ImageUtil.getGalleryIntent());
                break;
            case R.id.ConfirmationButton:
                boolean fieldsAreNotEmpty = true;
                for (EditText field : cannotBeEmptyFields) {
                    if (TextUtils.isEmpty(field.getText().toString())) {
                        Toast.makeText(this,
                                "No field can be empty!",
                                Toast.LENGTH_SHORT).show();
                        fieldsAreNotEmpty = false;
                        break;
                    }
                }
                if (imagePath == null) {
                    fieldsAreNotEmpty = false;
                }
                if (fieldsAreNotEmpty) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    Meal meal = new Meal(
                            mealNameText.getText().toString(),
                            descriptionText.getText().toString(),
                            "",
                            allergensInMeal,
                            new Date());//TODO: build the retrieval date
                    Task<String> task = vmodel.placeMeal(meal, imagePath);
                    task.addOnSuccessListener((mealId) -> {
                        double price = Double.parseDouble(priceText.getText().toString());
                        vmodel.createOrder(mealId,location,price).addOnSuccessListener(orderId -> startActivity(i));
                    });
                }

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
            case R.id.locationButton:
                Intent mapIntent = new Intent(PlaceMealActivity.this, PlacePinActivity.class);
                locationActivityResultLauncher.launch(mapIntent);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // tool bar Back Icon
        {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
