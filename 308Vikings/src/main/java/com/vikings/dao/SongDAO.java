package com.vikings.dao;

import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for basic Song actions
 */
@Repository
public class SongDAO {
    
    @Autowired
    SongMapper songMapper;
    
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
    
}
