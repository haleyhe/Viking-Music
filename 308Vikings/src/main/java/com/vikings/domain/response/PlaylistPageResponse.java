package com.vikings.domain.response;

import com.vikings.domain.Playlist;
import java.util.HashMap;

public class PlaylistPageResponse extends PageResponse {
    Playlist playlist;
    boolean following;

    public PlaylistPageResponse() {
    }
    
    public PlaylistPageResponse(String error) {
        super(error);
    }
    
    public PlaylistPageResponse(Playlist playlist, boolean following, HashMap<String, Boolean> savedSongs) {
        this.playlist = playlist;
        this.following = following;
        this.savedSongs = savedSongs;
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
    
    
}
