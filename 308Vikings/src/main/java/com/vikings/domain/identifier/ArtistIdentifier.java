package com.vikings.domain.identifier;

import com.vikings.domain.Artist;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a handle to an Artist.
 */
public class ArtistIdentifier implements Serializable {
    String id;
    String name;

    public ArtistIdentifier() {
    }

    public ArtistIdentifier(String id) {
        this.id = id;
    }

    public ArtistIdentifier(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final ArtistIdentifier other = (ArtistIdentifier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
