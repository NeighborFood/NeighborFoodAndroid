package com.epfl.neighborfood.neighborfoodandroid.models;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


/**
 * A NeighborFood user, storedin our database
 */
public class User extends Model {

    /* Keys of the data received from the server */
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PP_URI = "profilePictureURI";
    public static final String KEY_BIO = "bio";
    public static final String KEY_LINKS = "links";


    /* Fields concerning the User object */
    private String id = "";
    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String bio = "";
    private ArrayList<String> links = new ArrayList<>();
    private String ppUri;
    private List<String> subscribedIDs;
    private int numberSubscribers;

    /**
     * Create a new User object, holding database
     * information related to the user.
     * Most of values are given by tequila.
     *
     * @param id        ID of the user in database
     * @param email     E-mail of user
     * @param firstName First name of user
     * @param lastName  Last name of user
     */
    public User(String id, String email, String firstName, String lastName, String uri) {
        setId(id);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(firstName + lastName);
        setProfilePictureURI(uri);
        subscribedIDs = new ArrayList<>();
        numberSubscribers = 0;
    }


    public User() {
    }

    public List<String> getSubscribedIDs() {
        return this.subscribedIDs;
    }

    public void setSubscribedIDs(List<String> subscribedIDs) {
        this.subscribedIDs = subscribedIDs;
    }

    public int getNumberSubscribers() {
        return this.numberSubscribers;
    }

    public void setNumberSubscribers(int numberSubscribers) {
        this.numberSubscribers = numberSubscribers;
    }

    /**
     * @return Full name of the user in the format "First name Last name"
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username the username
     */
    private void setUsername(String username) {
        this.username = username;
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
     *
     * @param id ID of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return E-mail of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Define the e-mail of the user
     *
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
     *
     * @param firstName First name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Define the Last name of the user
     *
     * @param lastname Last name of the user
     */
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    /**
     * Getter for the profile picture URI
     *
     * @return URI
     */
    public String getProfilePictureURI() {
        return this.ppUri;
    }

    /**
     * Setter for the profile picture URI
     *
     * @param uri
     */
    public void setProfilePictureURI(String uri) {
        this.ppUri = uri;
    }

    /**
     * Getter for the bio of the user
     *
     * @return the bio
     */
    public String getBio() {
        return this.bio;
    }

    /**
     * Setter for the bio of the user
     *
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<String> getLinks() {
        return (ArrayList<String>) links.clone();
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }


    @NonNull
    @Override
    public String toString() {
        return "{\n" + KEY_ID + " : " + getId() + ", " +
                "\n" + KEY_EMAIL + " : " + getEmail() + ", " +
                "\n" + KEY_FIRSTNAME + " : " + getFirstName() + ", " +
                "\n" + KEY_LASTNAME + " : " + getLastName() + " " +
                "\n" + "}";
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        User c = (User) o;

        // Compare the data members and return accordingly
        return getId().equals(c.getId());
    }
}
