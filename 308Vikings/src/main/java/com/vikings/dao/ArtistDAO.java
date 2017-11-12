package com.vikings.dao;

import com.vikings.dao.mapper.ArtistMapper;
import com.vikings.domain.Artist;
import com.vikings.domain.Name;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDAO {
    
    @Autowired
    ArtistMapper artistMapper;
    
    public void updateArtist(Artist artist) {
        artistMapper.updateArtist(artist);
    }
    
    public void addRelatedName(String artistId, Name name) {
        artistMapper.addRelatedName(artistId, name);
    }
    
    public void addGenre(String artistId, String genre) {
        artistMapper.addGenre(artistId, genre);
    }
    
    public Artist getArtist(String id) {
        return artistMapper.getArtist(id);
    }
    
    public Artist getArtistAccount(String id, String password) {
        return artistMapper.getArtistAccount(id, password);
    }
    
    public Set<Artist> getAllArtistsForPayment() {
        return artistMapper.getAllArtistsForPayment();
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
