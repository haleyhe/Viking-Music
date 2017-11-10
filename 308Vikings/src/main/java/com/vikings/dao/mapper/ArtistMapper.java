package com.vikings.dao.mapper;

import com.vikings.domain.Artist;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;
import java.util.Set;

/**
 * Mapper class for ArtistDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/ArtistMapper.xml
 */
public interface ArtistMapper {
    
    public Artist getArtist(String id);
    
    public List<ArtistIdentifier> getRelatedArtists(String id);
    
    public Set<ArtistIdentifier> search(String query);
    
}

