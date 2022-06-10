package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealTest {
    private String id;
    private String name;
    private String shortDescription;
    private List<Allergen> allergens;
    private double price;
    private int imageId;

    @Before
    public void initTestVariables() {
        id = "id1";
        name = "Tofu";
        shortDescription = "Yumi tofu";
        allergens = new ArrayList<>();
        allergens.add(Allergen.EGGS);
        allergens.add(Allergen.HONEY);
        allergens.add(Allergen.SOY);
        price = 2.23;
        imageId = 5;
    }

    private Meal getNewMeal() {
        return new Meal(name, shortDescription, "android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon, allergens, null);
    }


    @Test
    public void bothConstructorWork() {
        //new Meal(name, shortDescription, longDescription, imageId);
        getNewMeal();
    }

    @Test
    public void getName() {
        Meal meal = getNewMeal();
        assertEquals(name, meal.getName());
    }

    @Test
    public void getDescription() {
        Meal meal = getNewMeal();
        assertEquals(shortDescription, meal.getDescription());
    }



    @Test
    public void getAllergens() {
        Meal meal = getNewMeal();
        assertArrayEquals(allergens.toArray(), meal.getAllergens().toArray());
    }



    @Test
    public void getImageId() {
        Meal meal = getNewMeal();
        //assertEquals(imageId, meal.getImageId());
    }
}
