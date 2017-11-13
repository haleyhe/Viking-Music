package com.vikings.domain;

import com.vikings.domain.identifier.ArtistIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LibraryArtist implements Serializable, Comparable<LibraryArtist> {
    Date dateAdded;
    ArtistIdentifier artistIdentifier;

    public LibraryArtist() {
    }
    
    public LibraryArtist(ArtistIdentifier artistIdentifier, Date dateAdded) {
        this.dateAdded = dateAdded;
        this.artistIdentifier = artistIdentifier;
    }

    public LibraryArtist(ArtistIdentifier artistIdentifier) {
        this.artistIdentifier = artistIdentifier;
    }

    
    public ArtistIdentifier getArtistIdentifier() {
        return artistIdentifier;
    }

    public void setArtistIdentifier(ArtistIdentifier artistIdentifier) {
        this.artistIdentifier = artistIdentifier;
    }
    
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.artistIdentifier);
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
        final LibraryArtist other = (LibraryArtist) obj;
        if (!Objects.equals(this.artistIdentifier, other.artistIdentifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(LibraryArtist o) {
        if (this.dateAdded == null || o.getDateAdded() == null)
            return 0;
        return dateAdded.compareTo(o.getDateAdded());
    }

    
}
