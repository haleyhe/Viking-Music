package com.vikings.domain;

import java.io.Serializable;

/**
 * Represents a User of the Database interface.
 */
public class DBUser implements Serializable {
    String id;
    String email;
    String password;
    String accessRequestReason;
    boolean artistInformationPermission;
    boolean artistAlbumPermission;
    boolean concertInformationPermission;
    boolean approved;
    Artist affiliatedArtist;
    
    public DBUser() {
        
    }
    
    public DBUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessRequestReason() {
        return accessRequestReason;
    }

    public void setAccessRequestReason(String accessRequestReason) {
        this.accessRequestReason = accessRequestReason;
    }

    public boolean hasArtistInformationPermission() {
        return artistInformationPermission;
    }

    public void setArtistInformationPermission(boolean artistInformationPermission) {
        this.artistInformationPermission = artistInformationPermission;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Artist getAffiliatedArtist() {
        return affiliatedArtist;
    }

    public void setAffiliatedArtist(Artist affiliatedArtist) {
        this.affiliatedArtist = affiliatedArtist;
    }

    public boolean isArtistAlbumPermission() {
        return artistAlbumPermission;
    }

    public void setArtistAlbumPermission(boolean artistAlbumPermission) {
        this.artistAlbumPermission = artistAlbumPermission;
    }

    public boolean hasConcertInformationPermission() {
        return concertInformationPermission;
    }

    public void setConcertInformationPermission(boolean concertInformationPermission) {
        this.concertInformationPermission = concertInformationPermission;
    }
}
