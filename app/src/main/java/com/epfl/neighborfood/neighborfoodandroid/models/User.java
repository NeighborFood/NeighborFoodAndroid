package com.epfl.neighborfood.neighborfoodandroid.models;



import android.net.Uri;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;


public class User extends Model implements Serializable {

    /* Keys of the data received from the server */
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";


    /* Fields concerning the User object */
    private String id = "";
    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private String bio = "";
    private ArrayList<String> links = new ArrayList<>();
    //private Profile _profile = null;
    private Uri ppUri;

    /**
     * Create a new User object, holding database
     * information related to the user.
     * Most of values are given by tequila.
     * @param id ID of the user in database
     * @param email E-mail of user
     * @param firstName First name of user
     * @param lastName Last name of user
     */
    public User(String id, String email, String firstName, String lastName) {
        setId(id);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User(){
    }


    /**
     * @return Full name of the user in the format "First name Last name"
     */
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * @return ID of the user corresponding to the one in the database
     */
    public String getId() {
        return id;
    }

    /**
     * Define the user ID and if a profile / avatar is attached,
     * also update their user ID.
     * @param id ID of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return E-mail of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define the e-mail of the user
     * @param email E-mail of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return First name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Define the First name of the user
     * @param firstName First name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Define the Last name of the user
     * @param lastname Last name of the user
     */
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    /**
     * Getter for the profile picture URI
     * @return URI
     */
    public Uri getProfilePictureURI(){
        return ppUri;
    }
    /**
     * Setter for the profile picture URI
     * @param uri
     */
    public void setProfilePictureURI(Uri uri){
        ppUri = uri == null ? Uri.EMPTY : uri;
    }

    /**
     * Getter for the bio of the user
     * @return the bio
     */
    public String getBio(){
        return bio;
    }
    /**
     * Setter for the bio of the user
     * @param bio
     */
    public void setBio(String bio){
        this.bio = bio;
    }

    public ArrayList<String> getLinks(){
        return (ArrayList<String>) links.clone();
    }
    public void setLinks(ArrayList<String>links){
        this.links = links;
    }



    @Override
    public String toString() {
        return "{\n" +
                KEY_ID + " : " + getId() + ", " + "\n" +
                KEY_EMAIL + " : " + getEmail() + ", " + "\n" +
                KEY_FIRSTNAME + " : " + getFirstName() + ", " + "\n" +
                KEY_LASTNAME + " : " + getLastName() + " " + "\n" +
                "}";
    }

}