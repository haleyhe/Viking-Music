package com.vikings.domain.identifier;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a handle to a Playlist.
 */
public class PlaylistIdentifier implements Serializable {
    String id;
    String name;
    UserIdentifier creator;

    public PlaylistIdentifier() {
    }
    
    public PlaylistIdentifier(String id) {
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

    public UserIdentifier getCreator() {
        return creator;
    }

    public void setCreator(UserIdentifier creator) {
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final PlaylistIdentifier other = (PlaylistIdentifier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
