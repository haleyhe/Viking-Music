/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain;

import com.vikings.domain.identifier.AlbumIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author HH
 */
public class LibraryAlbum implements Serializable  {
    Date dateAdded;
    AlbumIdentifier albumIndentifier;

    public LibraryAlbum(AlbumIdentifier albumIndentifier, Date dateAdded) {
        this.dateAdded = dateAdded;
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
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.dateAdded);
        hash = 47 * hash + Objects.hashCode(this.albumIndentifier);
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
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.albumIndentifier, other.albumIndentifier)) {
            return false;
        }
        return true;
    }
    
    
}
