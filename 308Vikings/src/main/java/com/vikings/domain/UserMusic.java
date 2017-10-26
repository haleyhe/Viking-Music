package com.vikings.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a User's music library and preferences.
 */
public class UserMusic implements Serializable {
    List<Song> savedSongs;
    List<Album> savedAlbums;
    List<Artist> followedArtists;
    List<Playlist> followedPlaylists;
    List<Song> history;
    List<Song> recentlyPlayed;
    
    public UserMusic() {
        
    }

    public List<Song> getSavedSongs() {
        return savedSongs;
    }

    public void setSavedSongs(List<Song> savedSongs) {
        this.savedSongs = savedSongs;
    }

    public List<Album> getSavedAlbums() {
        return savedAlbums;
    }

    public void setSavedAlbums(List<Album> savedAlbums) {
        this.savedAlbums = savedAlbums;
    }

    public List<Artist> getFollowedArtists() {
        return followedArtists;
    }

    public void setFollowedArtists(List<Artist> followedArtists) {
        this.followedArtists = followedArtists;
    }

    public List<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(List<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    public List<Song> getHistory() {
        return history;
    }

    public void setHistory(List<Song> history) {
        this.history = history;
    }

    public List<Song> getRecentlyPlayed() {
        return recentlyPlayed;
    }

    public void setRecentlyPlayed(List<Song> recentlyPlayed) {
        this.recentlyPlayed = recentlyPlayed;
    }
    
}
