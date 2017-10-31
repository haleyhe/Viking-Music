package com.vikings.domain.identifier;

import java.io.Serializable;

/**
 * Represents a handle to a Song.
 */
public class SongIdentifier implements Serializable {
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
