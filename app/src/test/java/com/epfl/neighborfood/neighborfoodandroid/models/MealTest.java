package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealTest {
    private String name;
    private String shortDescription;
    private String longDescription;
    private List<Allergen> allergens;
    private double price;
    private int imageId;

    @Before
    public void initTestVariables() {
        name = "Tofu";
        shortDescription = "Yumi tofu";
        longDescription = "Plant based proteins";
        allergens = new ArrayList<>();
        allergens.add(Allergen.EGGS);
        allergens.add(Allergen.HONEY);
        allergens.add(Allergen.SOY);
        price = 2.23;
        imageId = 5;
    }


    @Test
    public void bothConstructorWork() {
        new Meal(name, shortDescription, longDescription, imageId);
        new Meal(name, shortDescription, longDescription, imageId, allergens, price);
    }

    @Test
    public void getName() {
        Meal meal = new Meal(name, shortDescription, longDescription, imageId, allergens, price);
        assertEquals(name, meal.getName());
    }

    @Test
    public void getShortDescription() {
        Meal meal = new Meal(name, shortDescription, longDescription, imageId, allergens, price);
        assertEquals(shortDescription, meal.getShortDescription());
    }

    @Test
    public void getLongDescription() {
        Meal meal = new Meal(name, shortDescription, longDescription, imageId, allergens, price);
        assertEquals(longDescription, meal.getLongDescription());
    }

    @Test
    public void getAllergens() {
        Meal meal = new Meal(name, shortDescription, longDescription, imageId, allergens, price);
        assertArrayEquals(allergens.toArray(), meal.getAllergens().toArray());
    }

    @Test
    public void getPrice() {
        Meal meal = new Meal(name, shortDescription, longDescription, imageId, allergens, price);
        assertEquals(price, meal.getPrice(), 0.1);
    }

    @Test
    public void getImageId() {
        Meal meal = new Meal(name, shortDescription, longDescription, imageId, allergens, price);
        assertEquals(imageId, meal.getImageId());
    }
}