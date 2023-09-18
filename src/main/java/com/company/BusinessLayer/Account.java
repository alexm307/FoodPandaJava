package com.company.BusinessLayer;

import java.io.Serializable;

/**
 * Class created in order to store information about client, admin, employee, each having a different ID
 * will be -1 for admin, 0 for employee, >0 for clients
 */
public class Account implements Serializable {
    private String username;
    private String password;
    private int ID;

    public Account (String username, String password) {
        this.password = password;
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Basic method for showing teh class as a string.
     * @return the string created
     */
    public String toString() {
        String string = new String("username: " + this.username + " password: " + this.password + " ID: " + this.ID);
        return string;
    }
}
