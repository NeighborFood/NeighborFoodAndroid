package com.epfl.neighborfood.neighborfoodandroid.models;


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
    private Date retrievalDate;
    private String imageUri;
    private List<String> allergens;

    public Meal() {

    }

    public Meal(String id,String vendorID,String name, String shortDescription, String longDescription, String imageUri, List<String> allergens, double price, Date retrievalDate){
        this(name,shortDescription,longDescription,imageUri, allergens, price, retrievalDate);
        this.mealId = id;
        this.vendorID = vendorID;
    }



    /**
     * Complete constructor of a Meal
     * @param name
     * @param shortDescription
     * @param longDescription
     * @param imageUri
     * @param allergens
     * @param price
     * @param retrievalDate
     */
    public Meal(String name, String shortDescription, String longDescription, String imageUri, List<String> allergens, double price, Date retrievalDate) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageUri = imageUri;
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

    public String getImageUri() {
        return imageUri;
    }
    public void setImageUri(String imageUri){
        this.imageUri = imageUri;
    }

    /** getter for the meal id
     * @return the meal id
     */
    public String getMealId(){
        return mealId;
    }

    /** setter for the meal id
     * @param  mealId the meal id
     */
    public void setMealId(String mealId){
        this.mealId = mealId;
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

}
