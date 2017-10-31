package com.vikings.domain;

import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.SongIdentifier;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a User's music library and preferences.
 */
public class UserMusic implements Serializable {
    List<SongIdentifier> savedSongs;
    List<AlbumIdentifier> savedAlbums;
    List<ArtistIdentifier> followedArtists;
    List<Playlist> followedPlaylists;
    List<SongIdentifier> history;
    List<SongIdentifier> recentlyPlayed;
    
    public UserMusic() {
        
    }

    public List<SongIdentifier> getSavedSongs() {
        return savedSongs;
    }

    public void setSavedSongs(List<SongIdentifier> savedSongs) {
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

    public List<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(List<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    public List<SongIdentifier> getHistory() {
        return history;
    }

    public void setHistory(List<SongIdentifier> history) {
        this.history = history;
    }

    public List<SongIdentifier> getRecentlyPlayed() {
        return recentlyPlayed;
    }

    public void setRecentlyPlayed(List<SongIdentifier> recentlyPlayed) {
        this.recentlyPlayed = recentlyPlayed;
    }
    
}
