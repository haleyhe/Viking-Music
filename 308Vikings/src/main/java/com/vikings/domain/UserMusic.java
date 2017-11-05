package com.vikings.domain;

import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a User's music library and preferences.
 */
public class UserMusic implements Serializable {
    List<Song> savedSongs;
    List<AlbumIdentifier> savedAlbums;
    List<ArtistIdentifier> followedArtists;
    List<PlaylistIdentifier> followedPlaylists;
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

    public List<AlbumIdentifier> getSavedAlbums() {
        return savedAlbums;
    }

    public void setSavedAlbums(List<AlbumIdentifier> savedAlbums) {
        this.savedAlbums = savedAlbums;
    }

    public List<ArtistIdentifier> getFollowedArtists() {
        return followedArtists;
    }

    public void setFollowedArtists(List<ArtistIdentifier> followedArtists) {
        this.followedArtists = followedArtists;
    }

    public List<PlaylistIdentifier> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(List<PlaylistIdentifier> followedPlaylists) {
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
