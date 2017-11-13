/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain.requests;

import com.vikings.domain.Playlist;

/**
 *
 * @author HH
 */
public class PlaylistPageResponse extends PageResponse {
    Playlist playlist;

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
