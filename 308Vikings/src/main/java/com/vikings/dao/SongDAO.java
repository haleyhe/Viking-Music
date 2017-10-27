package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Artist;
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
        Song result = songMapper.getSong(id);
        List<Artist> artists = artistMapper.getArtistsForSong(id);
        result.setArtists(artists);
        return result;
    }
    
}
