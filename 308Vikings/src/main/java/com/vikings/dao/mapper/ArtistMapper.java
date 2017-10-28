package com.vikings.dao.mapper;

import com.vikings.domain.Artist;
import java.util.List;

/**
 * Mapper class for ArtistDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/ArtistMapper.xml
 */
public interface ArtistMapper {
    
    public Artist getArtist(String id);
    
}

