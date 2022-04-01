package com.epfl.neighborfood.neighborfoodandroid.models;


import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String name, shortDescription, longDescription;
    private double price;
    private int imageId;
    private List<Allergen> allergens;


    public Meal(String name, String shortDescription, String longDescription, int imageId) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageId = imageId;
    }

    public Meal(String name, String shortDescription, String longDescription, int imageId, List<Allergen> allergens, double price) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageId = imageId;
        this.allergens = allergens;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public List<Allergen> getAllergens() { return allergens; }

    public double getPrice() {  return price; }

    public int getImageId() {
        return imageId;
    }
}
