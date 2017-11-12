package com.vikings.domain;

import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Artist implements Serializable {
    String id;
    String name;
    Set<Name> relatedNames;
    String bio;
    int followerCount;
    int playCount;
    List<String> genres;
    List<AlbumIdentifier> albums;
    double royaltyRate;
    
    public Artist() {
    }
    
    public Artist(String id) {
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
    
    public Set<Name> getRelatedNames() {
        return relatedNames;
    }
    
    public void setRelatedNames(Set<Name> relatedNames) {
        this.relatedNames = relatedNames;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    
    public List<AlbumIdentifier> getAlbums() {
        return albums;
    }
    
    public void setAlbums(List<AlbumIdentifier> albums) {
        this.albums = albums;
    }

    public double getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(double royaltyRate) {
        this.royaltyRate = royaltyRate;
    }
    
    public ArtistIdentifier toArtistIdentifier() {
        return new ArtistIdentifier(this.id, this.name);
    }
    
}
