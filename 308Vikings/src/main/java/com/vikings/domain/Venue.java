package com.vikings.domain;

import java.io.Serializable;

/**
 * Represents a Concert venue.
 */
public class Venue implements Serializable {
    String id;
    String name;
    Address address;
    
    public Venue() {
        
    }
    
    public Venue(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
