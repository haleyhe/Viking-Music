package com.vikings.manager;

import com.vikings.dao.SongDAO;
import com.vikings.domain.Song;
import java.util.HashMap;
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
    
    Map<String, Song> songs;
    
    public SongManager() {
        this.songs = new HashMap<String, Song>();
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
    
}
