package com.vikings.manager;

import com.vikings.dao.ArtistDAO;
import com.vikings.domain.Artist;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for Artist and Artist Page actions
 */
@Service
public class ArtistManager {
    
    @Autowired
    ArtistDAO artistDAO;
    
    Map<String, Artist> artistCache;
    Map<String, List<ArtistIdentifier>> relatedArtistCache;
    
    public ArtistManager() {
        this.artistCache = new HashMap<>();
        this.relatedArtistCache = new HashMap<>();
    }
    
    /**
     * Retrieves Artist and adds it to the artists cache.
     * @param id
     *  ID of the desired Artist.
     * @return 
     *  Detailed Artist object.
     */
    public Artist getArtist(String id) {
        Artist artist = artistCache.get(id);
        if (artist == null) {
            artist = artistDAO.getArtist(id);
            artistCache.put(id, artist);
        }
        return artist;
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
        List<ArtistIdentifier> artists = relatedArtistCache.get(artistId);
        if (artists == null) {
            artists = artistDAO.getRelatedArtists(artistId);
            relatedArtistCache.put(artistId, artists);
        }
        return artists;
    }

    ArtistIdentifier getArtistIdentifier(String id) {
        Artist artist = getArtist(id);
        return new ArtistIdentifier(artist);
    }
    
}
