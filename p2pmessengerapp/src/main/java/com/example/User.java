package com.example;

import java.io.Serializable;

/**
 * Data clas which represents a user
 * 
 * @author Johnnie Meredith
 * @version 1.0
 */

public class User implements Serializable {

    /**
     * 
     * @param userName String representation of a Username
     * @param passWord String representation of a Password
     */
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    String userName;
    String passWord;

    /**
     * 
     * @return String Username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 
     * @param userName String representing a Username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return String Password
     */
    public String getPassWord() {
        return this.passWord;
    }

    /**
     * 
     * @param passWord String representing a Password
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
