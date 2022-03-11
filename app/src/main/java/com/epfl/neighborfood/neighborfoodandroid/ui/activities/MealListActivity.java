package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.ListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.ActivityMealListBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

import java.util.ArrayList;


public class MealListActivity extends AppCompatActivity{

    ActivityMealListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        findViewById(R.id.accountButton).setOnClickListener(this::onClick);
        findViewById(R.id.postButton).setOnClickListener(this::onClick);
        findViewById(R.id.messageButton).setOnClickListener(this::onClick);
        findViewById(R.id.signButton).setOnClickListener(this::onClick);

        int[] imageId = {R.drawable.poulet, R.drawable.couscous, R.drawable.paella,
                R.drawable.fondue, R.drawable.salade, R.drawable.soupe, R.drawable.tarte};

        String[] mealsName = {"Poulet au miel",
                "Couscous aux légumes",
                "Paella aux crevettes",
                "Fondue Moitié-Moitié",
                "Salade légère",
                "Soupe à l'oignon",
                "Tarte aux pommes"};

        String[] mealsShortDes = {"Un délicieux poulet au miel",
                "Un couscous comme à la maison",
                "Une paella traditionnelle",
                "Une bonne fondue",
                "Une salade à la tomate",
                "De la soupe à l'oignon",
                "Une bonne tarte aux pommes"};

        String[] mealsLongDes = {"Vous ne pourrez pas résister à ce savoureux poulet",
                "Ce couscous me fait penser à celui que me faisait mon grand-père",
                "Recette de paella directement d'Italie !",
                "blabla fondue",
                "blabla salade",
                "blabla soupe",
                "blabla tarte"};

        ArrayList<Meal> mealArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            Meal meal = new Meal(mealsName[i], mealsShortDes[i], mealsLongDes[i], imageId[i]);
            mealArrayList.add(meal);
        }

        ListAdapter listAdapter = new ListAdapter(MealListActivity.this, mealArrayList);

        binding.mealListView.setAdapter(listAdapter);
        binding.mealListView.setClickable(true);
        binding.mealListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MealListActivity.this, MealActivity.class);
                i.putExtra("name", mealsName[position]);
                i.putExtra("shortDes", mealsShortDes[position]);
                i.putExtra("longDes", mealsLongDes[position]);
                i.putExtra("imageid", imageId[position]);
                startActivity(i);
            }
        });
    }
    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.accountButton:
                 intent = new Intent(this, PersonalProfileActivity.class);
                break;
            case R.id.messageButton:
                intent = new Intent(this, ChatRoomActivity.class);
                break;
            case R.id.postButton:
                intent = new Intent(this, PlaceMealActivity.class);
                break;
            case R.id.signButton:
                intent = new Intent(this, SignUpActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}