package com.vikings.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Represents an artist in the Spotify system.
 */
public class Artist implements Serializable {
    String id;
    String name;
    Set<Name> relatedNames;
    String bio;
    int followerCount;
    int playCount;
    List<String> genres;
    
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
    
}
