package com.vikings.domain.response;

import com.vikings.domain.library.LibrarySong;
import java.util.HashMap;
import java.util.List;

public class LibrarySongResponse extends PageResponse{
    List<LibrarySong> songList;

    public LibrarySongResponse() {
    }
    
    public LibrarySongResponse(List<LibrarySong> songList) {
        this.songList = songList;
        HashMap<String,Boolean> saved = new HashMap();
        for (LibrarySong s : songList) {
            saved.put(s.getSong().getId(), Boolean.TRUE);
        }
        this.savedSongs = saved;
    }

    public List<LibrarySong> getSongList() {
        return songList;
    }

    public void setSongList(List<LibrarySong> songList) {
        this.songList = songList;
    }
        
}
