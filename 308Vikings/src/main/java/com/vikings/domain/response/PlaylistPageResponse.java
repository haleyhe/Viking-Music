/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain.response;

import com.vikings.domain.response.PageResponse;
import com.vikings.domain.Playlist;
import java.util.HashMap;

/**
 *
 * @author HH
 */
public class PlaylistPageResponse extends PageResponse {
    Playlist playlist;
    boolean following;

    public PlaylistPageResponse() {
    }
    
    public PlaylistPageResponse(Playlist playlist, boolean following) {
        this.playlist = playlist;
        this.following = following;
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
