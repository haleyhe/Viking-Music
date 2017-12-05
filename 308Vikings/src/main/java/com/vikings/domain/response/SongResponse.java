package com.vikings.domain.response;

import com.vikings.domain.Song;
import java.util.List;

public class SongResponse extends PageResponse {
    Song song;
    
    public SongResponse() {
    }
    
    public SongResponse(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public void setSongs(Song song) {
        this.song = song;
    }
    
}
