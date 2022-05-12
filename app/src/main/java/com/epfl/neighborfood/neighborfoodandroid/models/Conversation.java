package com.epfl.neighborfood.neighborfoodandroid.models;


import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import java.util.List;

/**
 * @author Mohamed Yassine Boukhari
 *
 */
public class Conversation extends Model {
    private List<User> users;
    private List<Message> messages;
    private String id;


    public Conversation(){
    }
    /**
     * constructor for the conversation class
     * @param users the set of users(2) in this conversation
     * @param messages the initial list of messages to start
     * the conversation with
     */
    public Conversation(List<User> users, List<Message> messages) {
        this.users = users;
        this.messages = messages;
    }

    /**
     * used to get the user with whom the current logged in
     * user is chatting
     * @return a User
     */
    public User chatter() {
        if (users != null) {
            User chatter = null;
            for (User usr: users){
                if(!usr.getId().equals(AuthenticatorFactory.getDependency().getCurrentUser().getId())){
                    chatter = usr;
                }
            }
            return chatter;
        }
        else return null;
    }

    /**
     * @return the set of users for this conversation
     */
    public List<User> getUsers(){
        return users;
    }

    /**
    }

    public String id() {
        return id;
    }
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
    public Message lastMessage() {
        if (messages != null) {
            return messages.get(messages.size() - 1);
        }
        return null;
    }

    public String id() {
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public List<Message> addMessage(Message msg){
        messages.add(msg);
        return  messages;
    }
}
