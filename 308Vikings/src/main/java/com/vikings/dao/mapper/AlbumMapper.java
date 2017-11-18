package com.vikings.dao.mapper;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for AlbumDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/AlbumMapper.xml
 */
public interface AlbumMapper {
    
    public Album getAlbum(String id);
    
    public List<AlbumIdentifier> getAllAlbums();
    
    public List<AlbumIdentifier> getAlbumsForArtist(String id);
    
    public List<AlbumIdentifier> getRecentAlbums();
    
    public List<AlbumIdentifier> search(@Param("query") String query, @Param("limit") Integer limit);   
}

