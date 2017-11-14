package com.vikings.domain.identifier;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserIdentifier other = (UserIdentifier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
