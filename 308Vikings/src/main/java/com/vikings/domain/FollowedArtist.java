/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain;

import com.vikings.domain.identifier.ArtistIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author HH
 */
public class FollowedArtist implements Serializable {
    Date dateAdded;
    ArtistIdentifier artistIdentifier;

    public FollowedArtist() {
    }
    
    public FollowedArtist(ArtistIdentifier artistIdentifier, Date dateAdded) {
        this.dateAdded = dateAdded;
        this.artistIdentifier = artistIdentifier;
    }

    public FollowedArtist(ArtistIdentifier artistIdentifier) {
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
        final FollowedArtist other = (FollowedArtist) obj;
        if (!Objects.equals(this.artistIdentifier, other.artistIdentifier)) {
            return false;
        }
        return true;
    }

    
}
