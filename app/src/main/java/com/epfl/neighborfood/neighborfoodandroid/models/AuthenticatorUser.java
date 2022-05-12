package com.epfl.neighborfood.neighborfoodandroid.models;

/**
 * An authenticator user is a user that is returned by the authentication system, which is not necessarily registered in our app.
 * This user can serve as building model for a user of our app (i.e. a user in our database)
 * For now, the ids of a user that is in the authenticator corresponds to the id of a user in our database
 */
public abstract class AuthenticatorUser {
    private final String id ;
    private final String email;
    private String firstName ;
    private String lastName ;
    private final String ppUri;

    /**
     * @param id the id of the user in the authenticator system
     * @param email the email of the user registered in the authenticator system
     * @param firstName the firstname
     * @param lastName the lastname of the user
     * @param ppUri the uri of the profile picture
     */
    public AuthenticatorUser(String id, String email, String firstName, String lastName, String ppUri){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ppUri = ppUri;
    }

    /**getter for the id
     * @return the id of the user
     */
    public String getId(){
        return id;
    }

    /** getter for the email
     * @return the email of the user
     */
    public String getEmail(){
        return email;
    }

    /** getter for the firstname
     * @param firstName the firstname to set
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /** setter for the lastname
     * @param lastName the lastname to set
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * @return the firstname of the user
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * @return the last name of the user
     */
    public String getLastName(){
        return lastName;
    }

    /** getter for the profile picture uri
     * @return the uri of the profile picture
     */
    public String getPpUri(){
        return ppUri;
    }

}
