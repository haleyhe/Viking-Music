package com.vikings.domain.response;

import com.vikings.domain.Song;
import java.util.Map;
import java.util.List;

public class HistoryResponse extends PageResponse{
    List<Song> songList;

    public HistoryResponse() {
    }

    public HistoryResponse(List<Song> history, Map<String, Boolean> savedSongs) {
        this.songList = history;
        this.savedSongs = savedSongs;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
    
    
}
