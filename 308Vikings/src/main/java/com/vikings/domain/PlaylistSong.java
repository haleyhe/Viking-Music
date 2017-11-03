package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a song in a specific Playlist.
 */
public class PlaylistSong extends Song implements Serializable {
    Date dateAdded;
    int playlistTrackNumber;
    
    public PlaylistSong() {
    }
    
    public PlaylistSong(String id) {
        this.id = id;
    }
    
    public PlaylistSong(Song song) {
        this.id = song.getId();
        this.album = song.getAlbum();
        this.artists = song.getArtists();
        this.duration = song.getDuration();
        this.explicit = song.isExplicit();
        this.lyrics = song.getLyrics();
        this.name = song.getName();
        this.numPlays = song.getNumPlays();
        this.discNumber = song.getDiscNumber();
        this.trackNumber = song.getTrackNumber();
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getPlaylistTrackNumber() {
        return playlistTrackNumber;
    }

    public void setPlaylistTrackNumber(int playlistTrackNumber) {
        this.playlistTrackNumber = playlistTrackNumber;
    }
    
    @Override
    public int compareTo(Song s) {
        if (s instanceof PlaylistSong) {
            return Integer.compare(this.playlistTrackNumber, ((PlaylistSong) s).getPlaylistTrackNumber());
        } else {
            int result = Integer.compare(this.discNumber, s.getDiscNumber());
            if (result != 0)
                return result;
            else
            return Integer.compare(this.trackNumber, s.getTrackNumber()); 
        }
    }
    
}
