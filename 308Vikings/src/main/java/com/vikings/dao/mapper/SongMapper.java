package com.vikings.dao.mapper;

import com.vikings.domain.Song;

/**
 * Mapper class for SongDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/SongMapper.xml
 */
public interface SongMapper {
    
    public Song getSong(String id);
    
}

