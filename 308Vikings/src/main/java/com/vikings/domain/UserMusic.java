package com.vikings.domain;

import com.vikings.domain.identifier.UserIdentifier;
import com.vikings.domain.library.LibrarySong;
import com.vikings.domain.library.LibraryPlaylist;
import com.vikings.domain.library.LibraryArtist;
import com.vikings.domain.library.LibraryAlbum;
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
    List<UserIdentifier> friends;
    
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

    public List<UserIdentifier> getFriends() {
        return friends;
    }

    public void setFriends(List<UserIdentifier> friends) {
        this.friends = friends;
    }
    
}
