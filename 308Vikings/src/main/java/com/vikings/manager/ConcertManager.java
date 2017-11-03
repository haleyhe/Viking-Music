package com.vikings.manager;

import com.vikings.dao.ConcertDAO;
import com.vikings.domain.Concert;
import java.util.HashMap;
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
    
    Map<String, Concert> concerts;
    
    public ConcertManager() {
        this.concerts = new HashMap<String, Concert>();
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
    
}
