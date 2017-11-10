package com.vikings.domain.identifier;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a handle to a User.
 */
public class UserIdentifier implements Serializable {
    String id;
    String name;
    
    public UserIdentifier() {
    }
    
    public UserIdentifier(String id, String name) {
        this.id = id;
        this.name = name;
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
    
}
