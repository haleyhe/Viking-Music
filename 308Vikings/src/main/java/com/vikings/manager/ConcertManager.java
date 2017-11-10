package com.vikings.manager;

import com.vikings.dao.ConcertDAO;
import com.vikings.domain.Concert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for Album and Album Page actions
 */
@Service
public class ConcertManager {
    
    @Autowired
    ConcertDAO concertDAO;
    
    Map<String, Concert> concertCache;
    Map<String, List<Concert>> concertsByArtistCache;
    
    public ConcertManager() {
        this.concertCache = new HashMap<String, Concert>();
        this.concertsByArtistCache = new HashMap<String, List<Concert>>();
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
        List<Concert> concerts = concertsByArtistCache.get(id);
        if (concerts == null) {
            concerts = concertDAO.getConcertsForArtist(id);
            concertsByArtistCache.put(id, concerts);
        }
        return concerts;
    }
    
}
