package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Song;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for basic Song actions
 */
@Repository
public class SongDAO {
    
    @Autowired
    SongMapper songMapper;
    
    @Autowired
    ArtistMapper artistMapper;
    
    /**
     * Gets information for a single Song.
     * @param id
     *  The Song ID.
     * @return 
     *  The detailed Song object.
     */
    public Song getSong(String id) {
        return songMapper.getSong(id);
    }
    
    /**
     * Gets the Songs for the Artist, sorted by play count.
     * @param id
     *  The Artist ID.
     * @return 
     *  List of Songs sorted by play count (most-played songs at the top of the list).
     */
    public List<Song> getTopSongsForArtist(String id) {
        return songMapper.getTopSongsForArtist(id);
    }
    
}
