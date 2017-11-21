package com.vikings.domain.response;

import com.vikings.domain.Playlist;
import java.util.HashMap;

public class PlaylistPageResponse extends PageResponse {
    Playlist playlist;
    boolean following;
    int numSongs;
    int totalDuration;

    public PlaylistPageResponse() {
    }
    
    public PlaylistPageResponse(String error) {
        super(error);
    }
    
    public PlaylistPageResponse(Playlist playlist, boolean following, HashMap<String, Boolean> savedSongs, int numSongs, int totalDuration) {
        this.playlist = playlist;
        this.following = following;
        this.savedSongs = savedSongs;
        this.numSongs = numSongs;
        this.totalDuration = totalDuration;
    }
    
    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }
    
    
}
