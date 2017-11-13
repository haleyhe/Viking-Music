package com.vikings.manager;

import com.vikings.dao.ConcertDAO;
import com.vikings.domain.Concert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcertManager {
    
    @Autowired
    ConcertDAO concertDAO;
    
    Map<String, Concert> concerts;
    Map<String, List<Concert>> concertsByArtist;
    
    public ConcertManager() {
        this.concerts = new HashMap<String, Concert>();
        this.concertsByArtist = new HashMap<String, List<Concert>>();
    }
    
    /**
     * Retrieves Concert and adds it to the concerts cache.
     * @param id
     *  ID of the desired Concerts.
     * @return 
     *  Detailed Concert object.
     */
    public Concert getConcert(String id) {
        Concert concert = concerts.get(id);
        if (concert == null) {
            concert = concertDAO.getConcert(id);
            concerts.put(id, concert);
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
        List<Concert> c = concertsByArtist.get(id);
        if (c == null) {
            c= concertDAO.getConcertsForArtist(id);
            concertsByArtist.put(id, c);
        }
        return c;
    }
    
}
