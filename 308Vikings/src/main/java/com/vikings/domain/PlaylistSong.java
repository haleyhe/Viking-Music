package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a song in a specific Playlist.
 */
public class PlaylistSong extends Song implements Serializable {
    Date timeAdded;
    int playlistTrackNumber;
    
    public PlaylistSong() {
    }
    
    public PlaylistSong(String id) {
        this.id = id;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public int getPlaylistTrackNumber() {
        return playlistTrackNumber;
    }

    public void setPlaylistTrackNumber(int playlistTrackNumber) {
        this.playlistTrackNumber = playlistTrackNumber;
    }
    
}
