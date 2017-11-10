package com.vikings.domain;

import com.vikings.domain.identifier.PlaylistIdentifier;
import com.vikings.domain.identifier.UserIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents a user-created Playlist.
 */
public class Playlist implements Serializable {
    String id;
    String name;
    UserIdentifier creator;
    Date creationDate;
    String description;
    boolean publiclyVisible;
    int numFollowers;
    List<PlaylistSong> songs;
    
    public Playlist() {
    }
    
    public Playlist(String id) {
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

    public UserIdentifier getCreator() {
        return creator;
    }

    public void setCreator(UserIdentifier creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPubliclyVisible() {
        return publiclyVisible;
    }

    public void setPubliclyVisible(boolean publiclyVisible) {
        this.publiclyVisible = publiclyVisible;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }

    public List<PlaylistSong> getSongs() {
        return songs;
    }

    public void setSongs(List<PlaylistSong> songs) {
        this.songs = songs;
    }
    
    public void moveSong(int oldTrackNum, int newTrackNum) {
        if (oldTrackNum <= songs.size() && newTrackNum <= songs.size() && newTrackNum > 0) {
            PlaylistSong song = songs.remove(oldTrackNum - 1);
            if (newTrackNum == songs.size() + 1)
                songs.add(song);
            else
                songs.add(newTrackNum - 1, song);
            
            // update song numbering
            for (int i = 0; i < songs.size(); i ++) {
                songs.get(i).setPlaylistTrackNumber(i + 1);
            }
        }
    }
    
    public void removeSong(int trackNum) {
        if (trackNum <= songs.size() && trackNum > 0) {
            songs.remove(trackNum - 1);
            for (int i = trackNum - 1; i < songs.size(); i++) {
                PlaylistSong song = songs.get(i);
                song.setPlaylistTrackNumber(song.getPlaylistTrackNumber() - 1);
            }
        }
    }
    
    public PlaylistIdentifier toPlaylistIdentifier() {
        return new PlaylistIdentifier(this.id, this.name, this.creator);
    }
    
}
