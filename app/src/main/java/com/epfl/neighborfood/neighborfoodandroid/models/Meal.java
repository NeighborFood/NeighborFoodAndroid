package com.epfl.neighborfood.neighborfoodandroid.models;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * meal class contains the information about the meal proposed
 */
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
    private String description;
    private Date retrievalDate;
    private String imageUri;
    private List<Allergen> allergens;

    public Meal() {

    }

    /**
     * Constructor for the meal
     *
     * @param id            meal ID
     * @param vendorID      ID of Vendor of the meal
     * @param name          name of the meal
     * @param description   description about the meal
     * @param imageUri      image URI of meal's picture
     * @param allergens     allergens contained by the meal
     * @param retrievalDate date of the retrieval of the meal
     */
    public Meal(String id, String vendorID, String name, String description, String imageUri, List<Allergen> allergens, Date retrievalDate) {
        this(name, description, imageUri, allergens, retrievalDate);
        this.mealId = id;
        this.vendorID = vendorID;
    }


    /**
     * Complete constructor of a Meal
     *
     * @param name
     * @param description
     * @param imageUri
     * @param allergens
     * @param retrievalDate
     */
    public Meal(String name, String description, String imageUri, List<Allergen> allergens, Date retrievalDate) {
        this.name = name;
        this.description = description;
        this.imageUri = imageUri;
        this.allergens = allergens;
        this.retrievalDate = retrievalDate;
    }

    /**
     * getter for the meal name
     *
     * @return name of meal
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the description of the meal
     *
     * @return meal's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for the description of the meal
     *
     * @param description description of meal
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter for the meal's allergens
     *
     * @return list of the allergens of the meal
     */
    public List<Allergen> getAllergens() {
        return allergens;
    }

    /**
     * getter for the meal's image uri
     *
     * @return meal's image uri
     */
    public String getImageUri() {
        return imageUri;
    }

    /**
     * setter for the meal's image uri
     *
     * @param imageUri meal's image uri
     */
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    /**
     * getter for the meal id
     *
     * @return the meal id
     */
    public String getMealId() {
        return mealId;
    }

    /**
     * setter for the meal id
     *
     * @param mealId the meal id
     */
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    /**
     * getter for the meal's vendor id
     *
     * @return the owner vendorID
     */
    public String getVendorID() {
        return vendorID;
    }

    /**
     * Setter for the meal's vendor id
     *
     * @return the owner vendorID
     */
    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

}
