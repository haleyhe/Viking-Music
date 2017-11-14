package com.vikings.domain.library;

import com.vikings.domain.identifier.AlbumIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LibraryAlbum implements Serializable, Comparable<LibraryAlbum>  {
    Date dateAdded;
    AlbumIdentifier albumIndentifier;

    public LibraryAlbum() {
    }

    public LibraryAlbum(AlbumIdentifier albumIndentifier, Date dateAdded) {
        this.dateAdded = dateAdded;
        this.albumIndentifier = albumIndentifier;
    }

    public LibraryAlbum(AlbumIdentifier albumIndentifier) {
        this.albumIndentifier = albumIndentifier;
    }
    
    

    public AlbumIdentifier getAlbumIdentifier() {
        return albumIndentifier;
    }

    public void setAlbumIdentifier(AlbumIdentifier album) {
        this.albumIndentifier = album;
    }
    
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.albumIndentifier);
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
        final LibraryAlbum other = (LibraryAlbum) obj;
        if (!Objects.equals(this.albumIndentifier, other.albumIndentifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(LibraryAlbum o) {
        if (this.dateAdded == null || o.getDateAdded() == null)
            return 0;
        return dateAdded.compareTo(o.getDateAdded());
    }

    
    
    
}
