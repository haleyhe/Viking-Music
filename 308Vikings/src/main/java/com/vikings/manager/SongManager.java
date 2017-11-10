package com.vikings.manager;

import com.vikings.dao.SongDAO;
import com.vikings.domain.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for Song actions
 */
@Service
public class SongManager {
    
    @Autowired
    SongDAO songDAO;
    
    Map<String, Song> songCache;
    
    public SongManager() {
        this.songCache = new HashMap<>();
    }
    
    /**
     * Retrieves Song and adds it to the songs cache.
     * @param id
     *  ID of the desired Song.
     * @return 
     *  Detailed Song object.
     */
    public Song getSong(String id) {
        Song song = songCache.get(id);
        if (song == null) {
            song = songDAO.getSong(id);
            songCache.put(id, song);
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
    
}
