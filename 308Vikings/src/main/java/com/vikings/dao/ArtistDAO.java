package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.domain.Artist;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for Artist and Artist Page actions
 */
@Repository
public class ArtistDAO {
    
    @Autowired
    ArtistMapper artistMapper;
    
    public Artist getArtist(String id) {
        return artistMapper.getArtist(id);
    }
    
    /**
     * Returns a list of Artist who have overlapping genres with the given Artist.
     * The list is ordered by number of overlapping genres (first in list =
     * most "related")
     * @param artistId
     *  ID of the Artist to get related Artists for.
     * @return 
     *  An ordered list of related ArtistIdentifiers.
     */
    public List<ArtistIdentifier> getRelatedArtists(String artistId) {
        return artistMapper.getRelatedArtists(artistId);
    }
    
    public Set<ArtistIdentifier> search(String query) {
        // search regex for mysql
        query = "%" + query + "%";
        return artistMapper.search(query);
    }
    
}
