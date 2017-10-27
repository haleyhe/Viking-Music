package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.domain.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for Artist and Artist Page actions
 */
@Repository
public class ArtistDAO {
    
    @Autowired
    ArtistMapper artistMapper;
    
    /**
     * Gets information for a single Artist.
     * @param id
     *  The Artist ID.
     * @return 
     *  The detailed Artist object.
     */
    public Artist getArtist(String id) {
        return artistMapper.getArtist(id);
    }
    
}
