package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.dao.mapper.ConcertMapper;
import com.vikings.domain.Artist;
import com.vikings.domain.Concert;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for Concert and Concert Page actions
 */
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
        
        // ConcertMapper returns dummy Artist objects with just the IDs.
        // Make calls to artistMapper for detailed Artist info.
        List<Artist> detailedArtists = new ArrayList<Artist>();
        for (Artist artist : concert.getArtists()) {
            Artist detailedArtist = artistMapper.getArtist(artist.getId());
            detailedArtists.add(detailedArtist);
        }
        concert.setArtists(detailedArtists);
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
        
        for (Concert concert : concerts) {
            // ConcertMapper returns dummy Artist objects with just the IDs.
            // Make calls to artistMapper for detailed Artist info.
            // (this is cached so don't worry about the repeat calls)
            List<Artist> detailedArtists = new ArrayList<Artist>();
            for (Artist artist : concert.getArtists()) {
                Artist detailedArtist = artistMapper.getArtist(artist.getId());
                detailedArtists.add(detailedArtist);
            }
            concert.setArtists(detailedArtists);
        }
        
        return concerts;
    }
    
}
