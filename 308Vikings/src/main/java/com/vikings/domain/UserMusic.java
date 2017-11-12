package com.vikings.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Represents a User's music library and preferences.
 */
public class UserMusic implements Serializable {
    Set<LibrarySong> savedSongs;
    Set<LibraryAlbum> savedAlbums;
    Set<LibraryArtist> followedArtists;
    Set<LibraryPlaylist> followedPlaylists;
    List<Song> history;
    List<Song> recentlyPlayed;
    
    public UserMusic() {    
    }

    public Set<LibrarySong> getSavedSongs() {
        return savedSongs;
    }

    public void setSavedSongs(Set<LibrarySong> savedSongs) {
        this.savedSongs = savedSongs;
    }

    public Set<LibraryAlbum> getSavedAlbums() {
        return savedAlbums;
    }

    public void setSavedAlbums(Set<LibraryAlbum> savedAlbums) {
        this.savedAlbums = savedAlbums;
    }

    public Set<LibraryArtist> getFollowedArtists() {
        return followedArtists;
    }

    public void setFollowedArtists(Set<LibraryArtist> followedArtists) {
        this.followedArtists = followedArtists;
    }

    public Set<LibraryPlaylist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(Set<LibraryPlaylist> followedPlaylists) {
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
