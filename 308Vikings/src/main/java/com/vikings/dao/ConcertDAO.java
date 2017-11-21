package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.dao.mapper.ConcertMapper;
import com.vikings.domain.Artist;
import com.vikings.domain.Concert;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConcertDAO {
    
    @Autowired
    ConcertMapper concertMapper;
    
    @Autowired
    ArtistMapper artistMapper;
    
    /**
     * Gets information for a single Concert.
     * @param id
     *  The ID of the desired Concert.
     * @return 
     *  The detailed Concert object.
     */
    public Concert getConcert(String id) {
        Concert concert = concertMapper.getConcert(id);
        
        return concert;
    }
    
    /**
     * Gets all Concerts for an Artist, sorted by ascending date.
     * @param id
     *  The Artist ID.
     * @return 
     *  List of detailed Concert objects.
     */
    public List<Concert> getConcertsForArtist(String id) {
        List<Concert> concerts = concertMapper.getConcertsForArtist(id);
        
        return concerts;
    }
    
    public List<Concert> getConcertsForArtists(Set<ArtistIdentifier> artists) {
        return concertMapper.getConcertsForArtists(artists);
    }
    
}
