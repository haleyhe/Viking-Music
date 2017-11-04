package com.vikings.domain.identifier;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a handle to a Playlist.
 */
public class PlaylistIdentifier implements Serializable {
    String id;
    String name;
    UserIdentifier creator;

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

    public UserIdentifier getCreator() {
        return creator;
    }

    public void setCreator(UserIdentifier creator) {
        this.creator = creator;
    }
    
}
