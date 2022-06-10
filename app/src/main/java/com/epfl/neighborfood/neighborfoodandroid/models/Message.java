package com.epfl.neighborfood.neighborfoodandroid.models;


import java.util.Date;

/**
 * Message class contains the details of a conversation's single message
 */
public class Message extends Model {
    private String content;
    private String sender;
    private Date date;


    public Message(String messageText, String sender) {
        this(messageText, sender, new Date());
    }

    /**
     * Constructor for the message
     *
     * @param messageText content of the message
     * @param sender      sender of the message
     * @param date        sending date of the message
     */
    public Message(String messageText, String sender, Date date) {
        this.content = messageText;
        this.sender = sender;
        this.date = date;
    }

    public Message() {
    }

    /**
     * Getter for the content of the message
     *
     * @return content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for the content of the message
     *
     * @param content content of the message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter of the message's sender
     *
     * @return sender of the message
     */
    public String getSender() {
        return sender;
    }

    /**
     * Setter for the message's sender
     *
     * @param sender Sender of the message
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Getter for the message's date
     *
     * @return Date of message
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the message's date
     *
     * @param date Date of message
     */
    public void setDate(Date date) {
        this.date = date;
    }


}
