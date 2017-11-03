package com.vikings.domain.identifier;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a handle to a User.
 */
public class UserIdentifier implements Serializable {
    String id;
    String name;

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
