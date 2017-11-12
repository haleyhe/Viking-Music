package com.vikings.manager;

import com.vikings.dao.SongDAO;
import com.vikings.domain.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongManager {
    
    @Autowired
    SongDAO songDAO;
    
    Map<String, Song> songs;
    
    public SongManager() {
        this.songs = new HashMap<>();
    }
    
    /**
     * Retrieves Song and adds it to the songs cache.
     * @param id
     *  ID of the desired Song.
     * @return 
     *  Detailed Song object.
     */
    public Song getSong(String id) {
        Song song = songs.get(id);
        if (song == null) {
            song = songDAO.getSong(id);
            songs.put(id, song);
        }
        return song;
    }
    
    /**
     * Gets the Songs for the Artist, sorted by play count.
     * @param id
     *  The Artist ID.
     * @return 
     *  List of Songs sorted by play count (most-played songs at the top of the list).
     */
    public List<Song> getTopSongsForArtist(String id) {
        return songDAO.getTopSongsForArtist(id);
    }
    
    public Set<Song> search(String query) {
        return songDAO.search(query);
    }
    
}
