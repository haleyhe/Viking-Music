package com.vikings.domain;

import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Represents a User's music library and preferences.
 */
public class UserMusic implements Serializable {
    Set<LibrarySong> savedSongs;
    Set<LibraryAlbum> savedAlbums;
    Set<FollowedArtist> followedArtists;
    Set<PlaylistIdentifier> followedPlaylists;
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

    public Set<FollowedArtist> getFollowedArtists() {
        return followedArtists;
    }

    public void setFollowedArtists(Set<FollowedArtist> followedArtists) {
        this.followedArtists = followedArtists;
    }
    
    public Set<PlaylistIdentifier> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(Set<PlaylistIdentifier> followedPlaylists) {
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
