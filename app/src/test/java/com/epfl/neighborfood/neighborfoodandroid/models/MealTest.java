package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

public class MealTest {
    private String name = "Tofu";
    private String shortDescription = "Yumi tofu";
    private String longDescription = "Plant based proteins";
    private String allergens = "Wheat";
    private double price = 2.23;
    private int imageId = 5;


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
        assertEquals(allergens, meal.getAllergens());
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