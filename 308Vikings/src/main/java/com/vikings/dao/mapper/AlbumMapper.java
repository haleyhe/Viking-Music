package com.vikings.dao.mapper;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.List;

/**
 * Mapper class for AlbumDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/AlbumMapper.xml
 */
public interface AlbumMapper {
    
    public Album getAlbum(String id);
    
    public List<AlbumIdentifier> getRecentAlbums();
    
}

