package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {
    String id;
    String name;
    List<Artist> artists;
    Date releaseDate;
    List<Song> songs;
    
    public Album() {
        this.id = java.util.UUID.randomUUID().toString();
    }
    
    public Album(String id) {
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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
}
