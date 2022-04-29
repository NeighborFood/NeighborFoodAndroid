package com.epfl.neighborfood.neighborfoodandroid.models;


import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import java.util.List;
import java.util.Set;

/**
 * @author Mohamed Yassine Boukhari
 *
 */
public class Conversation extends Model {
    private Set<User> users;
    private List<Message> messages;

    /**
     * constructor for the conversation class
     * @param users the set of users(2) in this conversation
     * @param messages the initial list of messages to start
     * the conversation with
     */
    public Conversation(Set<User> users, List<Message> messages) {
        this.users = users;
        this.messages = messages;
    }

    /**
     * used to get the user with whom the current logged in
     * user is chatting
     * @return a User
     */
    public User getChatter() {
        User chatter = null;
        for (User usr: users){
            if(!usr.getId().equals(AuthenticatorFactory.getDependency().getCurrentUser().getId())){
                chatter = usr;
            }
        }
        return chatter;
    }

    /**
     * @return the set of users for this conversation
     */
    public Set<User> getUsers(){
        return users;
    }

    /**
     * the messages sent in this conversation
     * @return
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * get the last message sent by one of the users in
     * this conversation
     * @return
     */
    public Message getLastMessage() {
        if (messages != null) {
            return messages.get(messages.size() - 1);
        }
        return null;
    }
}
