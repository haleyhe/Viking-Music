package com.vikings.domain;

import java.io.Serializable;

/**
 * Represents a full name.
 * This is used for Artist related names and
 * Payment information.
 */
public class Name implements Serializable {
    String firstName;
    String lastName;
    
    public Name() {
        
    }
    
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
