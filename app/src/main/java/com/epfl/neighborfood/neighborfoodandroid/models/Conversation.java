package com.epfl.neighborfood.neighborfoodandroid.models;


import java.util.List;

/**
 * @author Mohamed Yassine Boukhari
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
     * @return the set of users for this conversation
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     * }
     * <p>
     * public String id() {
     * return id;
     * }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addMessage(Message msg) {
        messages.add(msg);
    }
}
