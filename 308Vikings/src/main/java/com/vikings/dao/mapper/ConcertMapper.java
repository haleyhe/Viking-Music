package com.vikings.dao.mapper;

import com.vikings.domain.Concert;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for ConcertDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/ConcertMapper.xml
 */
public interface ConcertMapper {
    
    public Concert getConcert(String id);
    
    public List<Concert> getConcertsForArtist(String id);
    
    public List<Concert> getConcertsForArtists(@Param("artists") List<ArtistIdentifier> artists);
    
}

