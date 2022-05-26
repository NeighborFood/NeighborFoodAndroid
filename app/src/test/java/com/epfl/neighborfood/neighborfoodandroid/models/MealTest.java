package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealTest {
    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private List<String> allergens;
    private double price;
    private int imageId;

    @Before
    public void initTestVariables() {
        id = "id1";
        name = "Tofu";
        shortDescription = "Yumi tofu";
        longDescription = "Plant based proteins";
        allergens = new ArrayList<>();
        allergens.add(Allergen.EGGS.getLabel());
        allergens.add(Allergen.HONEY.getLabel());
        allergens.add(Allergen.SOY.getLabel());
        price = 2.23;
        imageId = 5;
    }

    private Meal getNewMeal() {
        return new Meal(id,name, shortDescription, longDescription, imageId, allergens, price, null);
    }


    @Test
    public void bothConstructorWork() {
        new Meal(name, shortDescription, longDescription, imageId);
        getNewMeal();
    }

    @Test
    public void getName() {
        Meal meal = getNewMeal();
        assertEquals(name, meal.getName());
    }

    @Test
    public void getShortDescription() {
        Meal meal = getNewMeal();
        assertEquals(shortDescription, meal.getShortDescription());
    }

    @Test
    public void getLongDescription() {
        Meal meal = getNewMeal();
        assertEquals(longDescription, meal.getLongDescription());
    }

    @Test
    public void getAllergens() {
        Meal meal = getNewMeal();
        assertArrayEquals(allergens.toArray(), meal.getAllergens().toArray());
    }

    @Test
    public void getPrice() {
        Meal meal = getNewMeal();
        assertEquals(price, meal.getPrice(), 0.1);
    }

    @Test
    public void getImageId() {
        Meal meal = getNewMeal();
        assertEquals(imageId, meal.getImageId());
    }
}