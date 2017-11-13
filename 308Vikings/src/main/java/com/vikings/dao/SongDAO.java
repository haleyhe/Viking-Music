package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Song;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SongDAO {
    
    @Autowired
    SongMapper songMapper;
    
    @Autowired
    ArtistMapper artistMapper;
    
    public Song getSong(String id) {
        return songMapper.getSong(id);
    }
    
    /**
     * Gets the Songs for the Artist, sorted by play count.
     * @param id
     *  The Artist ID.
     * @return 
     *  List of Songs sorted by play count (most-played songs at the top of the list).
     */
    public List<Song> getTopSongsForArtist(String id) {
        return songMapper.getTopSongsForArtist(id);
    }
    
    public Set<Song> search(String query) {
        // search regex for mysql
        query = "%" + query + "%";
        return songMapper.search(query);
    }
    
    public Set<Song> getArtistSongsForPayment(String id, Date startDate, Date endDate) {
        return songMapper.getArtistSongsForPayment(id, startDate, endDate);
    }
    
}
