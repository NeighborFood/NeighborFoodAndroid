package com.epfl.neighborfood.neighborfoodandroid.models;


import java.util.Date;


public class Message extends Model {
    private String content;
    private String sender;
    private Date date;


    public Message(String messageText, String sender) {
        this(messageText, sender, new Date());
    }

    public Message(String messageText, String sender, Date date) {
        this.content = messageText;
        this.sender = sender;
        this.date = date;
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
