package com.epfl.neighborfood.neighborfoodandroid.models;


import java.io.Serializable;
import java.util.List;

public class Meal extends Model implements Serializable {
    private final String name;
    private final String shortDescription;
    private final String longDescription;
    private double price;
    private final int imageId;
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

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public double getPrice() {
        return price;
    }

    public int getImageId() {
        return imageId;
    }
}
