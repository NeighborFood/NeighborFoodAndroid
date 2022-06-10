package com.epfl.neighborfood.neighborfoodandroid.models;


import java.util.List;

/**
 * Conversation Class contains all the messages shared between two user
 */
public class Conversation extends Model {
    private List<String> users;
    private List<Message> messages;
    private String id;


    public Conversation() {
    }

    /**
     * constructor for the conversation class
     *
     * @param users    the set of users(2) in this conversation
     * @param messages the initial list of messages to start
     *                 the conversation with
     */
    public Conversation(String id, List<String> users, List<Message> messages) {
        this.users = users;
        this.messages = messages;
        this.id = id;
    }

    /**
     * used to get the user with whom the current logged in
     * user is chatting
     *
     * @return a User
     */
    public String chatter(String authenticatedID) {
        if (users != null) {
            String chatter = null;
            for (String usr : users) {
                if (!usr.equals(authenticatedID)) {
                    chatter = usr;
                }
            }
            return chatter;
        }
        return null;
    }

    /**
     * Getter for the conversation's users
     *
     * @return the set of users for this conversation
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     * the messages sent in this conversation
     *
     * @return the list of messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * get the last message sent by one of the users in
     * this conversation
     *
     * @return the last message
     */
    public Message lastMessage() {
        if (messages != null && messages.size() > 0) {
            return messages.get(messages.size() - 1);
        }
        return null;
    }

    /**
     * getter for the conversation's ID
     *
     * @return ID of the conversation
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the conversation's ID
     *
     * @param id ID of the conversation
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Adds a message to the messages list
     *
     * @param msg message to be added
     */
    public void addMessage(Message msg) {
        messages.add(msg);
    }
}
