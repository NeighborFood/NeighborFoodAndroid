package com.epfl.neighborfood.neighborfoodandroid.models;


import android.location.Location;

import java.io.Serializable;
import java.util.List;

public class Meal extends Model implements Serializable {
    /* Keys of the data received from the server */
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PP_URI = "profilePictureURI";
    public static final String KEY_BIO = "bio";
    public static final String KEY_LINKS = "links";


    private String mealId;
    private String vendorID;
    private String name;
    private String shortDescription;
    private String longDescription;
    private double price;
    private int imageId;
    private List<Allergen> allergens;
    private Location pickupLocation;

    /**
     * @param id the id to set
     * @return a new meal with all the attributes the same as this, but with a different id
     */
    public Meal copyWithId(String id){
        return new Meal(id,vendorID,name,shortDescription,longDescription,imageId);
    }
    public Meal(String id,String vendorID,String name, String shortDescription, String longDescription, int imageId){
        this(name,shortDescription,longDescription,imageId);
        this.mealId = id;
        this.vendorID = vendorID;
    }
    public Meal(String name, String shortDescription, String longDescription, int imageId) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageId = imageId;
    }

    public Meal(String name, String shortDescription, String longDescription, int imageId, List<Allergen> allergens, double price, Location pickupLocation) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageId = imageId;
        this.allergens = allergens;
        this.price = price;
        this.pickupLocation = pickupLocation;
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

    /** getter for the meal id
     * @return the meal id
     */
    public String getMealId(){
        return mealId;
    }

    /** getter for the meal id
     * @return the meal id
     */
    public void setMealId(String id){
        mealId = id;
    }
    /** getter for the meal's vendor id
     * @return the owner vendorID
     */
    public String getVendorID(){
        return vendorID;
    }

    /** Setter for the meal's vendor id
     * @return the owner vendorID
     */
    public void setVendorID(String vendorID){
        this.vendorID = vendorID;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
}
