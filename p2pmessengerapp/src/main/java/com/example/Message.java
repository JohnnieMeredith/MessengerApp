package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Message is a dataclass representing a Message
 * 
 * @author Johnnie Meredith
 * @version 1.0
 */

public class Message implements Serializable {
    private LocalDateTime dateTimeCreated;
    private String userName;
    private String text;

    public Message() {
    }

    /**
     * 
     * @param dateTimeCreated LocalDateTime representing now
     * @param userName        String denoting the user of origin
     * @param text            String text content of the message
     */
    public Message(LocalDateTime dateTimeCreated, String userName, String text) {
        this.dateTimeCreated = dateTimeCreated;
        this.userName = userName;
        this.text = text;
    }

    /**
     * 
     * @param dateTimeCreated LocalDateTime represents message creation time
     */
    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    /**
     * 
     * @return String text content
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @return String Username from where this originated
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 
     * @param userName Username string used for denoting origin
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @param text String text content of message to be delivered
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" + " dateTimeCreated='" + getDateTimeCreated() + "'" + ", userName='" + getUserName() + "'"
                + ", text='" + getText() + "'" + "}";
    }

    public LocalDateTime getDateTimeCreated() {
        return this.dateTimeCreated;
    }

}
