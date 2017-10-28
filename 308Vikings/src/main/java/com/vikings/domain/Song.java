package com.vikings.domain;

import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a song in the Spotify system.
 */
public class Song implements Serializable {
    String id;
    String name;
    AlbumIdentifier album;
    List<ArtistIdentifier> artists;
    String lyrics;
    int duration;
    boolean explicit;
    int discNumber;
    int trackNumber;
    int numPlays;
    
    public Song() {
    }
    
    public Song(String id) {
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

    public AlbumIdentifier getAlbum() {
        return album;
    }

    public void setAlbum(AlbumIdentifier album) {
        this.album = album;
    }

    public List<ArtistIdentifier> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistIdentifier> artists) {
        this.artists = artists;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getNumPlays() {
        return numPlays;
    }

    public void setNumPlays(int numPlays) {
        this.numPlays = numPlays;
    }
    
    public void incrementNumPlays() {
        this.numPlays += 1;
    }
    
}
