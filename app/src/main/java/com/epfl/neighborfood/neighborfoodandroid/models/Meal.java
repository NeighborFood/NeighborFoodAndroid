package com.epfl.neighborfood.neighborfoodandroid.models;


import java.io.Serializable;
import java.util.List;

public class Meal extends Model implements Serializable {
    private String name;
    private String shortDescription;
    private String longDescription;
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

    public Meal(){
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
