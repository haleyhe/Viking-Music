package com.vikings.domain.response;

import com.vikings.domain.Song;
import java.util.List;

public class SongsResponse extends PageResponse {
    List<Song> songs;
    
    public SongsResponse() {
    }
    
    public SongsResponse(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
}
