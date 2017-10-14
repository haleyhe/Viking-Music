package com.vikings.domain;

import java.io.Serializable;
import java.util.List;

public class Artist implements Serializable {
    String id;
    String name;
    int followerCount;
    int playCount;
    List<String> genres;
    
    public Artist() {
        this.id = java.util.UUID.randomUUID().toString();
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
