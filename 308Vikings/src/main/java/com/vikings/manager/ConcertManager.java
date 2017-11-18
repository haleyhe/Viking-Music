package com.vikings.manager;

import com.vikings.dao.ConcertDAO;
import com.vikings.domain.Concert;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcertManager {
    
    @Autowired
    ConcertDAO concertDAO;
    
    Map<String, Concert> concertCache;
    Map<String, List<Concert>> concertsByArtistCache;
    
    public ConcertManager() {
        this.concertCache = new HashMap<>();
        this.concertsByArtistCache = new HashMap<>();
    }
    
    /**
     * Retrieves Concert and adds it to the concerts cache.
     * @param id
     *  ID of the desired Concerts.
     * @return 
     *  Detailed Concert object.
     */
    public Concert getConcert(String id) {
        Concert concert = concertCache.get(id);
        if (concert == null) {
            concert = concertDAO.getConcert(id);
            concertCache.put(id, concert);
        }
        return concert;
    }
    
    /**
     * Retrieves all Concerts for an Artist, sorted by
     * ascending date.
     * @param id
     *  Artist ID.
     * @return 
     *  List of detailed Concert objects.
     */
    public List<Concert> getConcertsForArtist(String id) {
        List<Concert> c = concertsByArtistCache.get(id);
        if (c == null) {
            c= concertDAO.getConcertsForArtist(id);
            concertsByArtistCache.put(id, c);
        }
        return c;
    }
    
    public List<Concert> getConcertsForArtists(Set<ArtistIdentifier> artists) {
        return concertDAO.getConcertsForArtists(artists);
    }
    
}
