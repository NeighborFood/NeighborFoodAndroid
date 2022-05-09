package com.epfl.neighborfood.neighborfoodandroid.models;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Meal extends Model implements Serializable {
    private String id;
    private String vendorID;
    private String name;
    private String shortDescription;
    private String longDescription;
    private double price;
    private Date retrievalDate;
    private int imageId;
    private List<String> allergens;

    /**
     * @param id the id to set
     * @return a new meal with all the attributes the same as this, but with a different id
     */
    public Meal copyWithId(String id){
        return new Meal(id,vendorID,name,shortDescription,longDescription,imageId, allergens, price, retrievalDate);
    }
    public Meal(String id,String vendorID,String name, String shortDescription, String longDescription, int imageId, List<String> allergens, double price, Date retrievalDate){
        this(name,shortDescription,longDescription,imageId, allergens, price, retrievalDate);
        this.id = id;
        this.vendorID = vendorID;
    }
    public Meal(String name, String shortDescription, String longDescription, int imageId) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageId = imageId;
    }

    /**
     * Complete constructor of a Meal
     * @param name
     * @param shortDescription
     * @param longDescription
     * @param imageId
     * @param allergens
     * @param price
     * @param retrievalDate
     */
    public Meal(String name, String shortDescription, String longDescription, int imageId, List<String> allergens, double price, Date retrievalDate) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageId = imageId;
        this.allergens = allergens;
        this.price = price;
        this.retrievalDate = retrievalDate;
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

    public List<String> getAllergens() {
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
        return id;
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
