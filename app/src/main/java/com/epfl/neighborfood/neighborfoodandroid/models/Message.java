package com.epfl.neighborfood.neighborfoodandroid.models;


import java.util.Date;


public class Message extends Model{
    private String content;
    private User sender;
    private User receiver;
    private Date date;



    public Message(String messageText, User sender, User receiver) {
        this.content = messageText;
        this.sender = sender;
        this.receiver = receiver;
        date = new Date();
    }

    public Message(String messageText, User sender, User receiver, Date date) {
        this.content = messageText;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public Message(){
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


}
