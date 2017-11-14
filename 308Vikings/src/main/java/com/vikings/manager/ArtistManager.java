package com.vikings.manager;

import com.vikings.dao.ArtistDAO;
import com.vikings.domain.Artist;
import com.vikings.domain.Name;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.request.UpdateArtistRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ArtistManager {
    
    @Autowired
    UserAccountManager userAccountManager;
    
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
     * Retrieves Artist for account login.
     * @param id
     *  Artist ID
     * @param password
     *  Artist password (unhashed)
     * @return
     *  Detailed Artist object if success, null if invalid ID/password.
     */
    public Artist getArtistAccount(String id, String password) {
        password = userAccountManager.hashPassword(password);
        return artistDAO.getArtistAccount(id, password);
        
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
    
    /**
     * Retrieves an up-to-date, alphabetical list of all the artists.
     * @return 
     *  List of artistIdentifiers.
     */
    public List<ArtistIdentifier> getAllArtists() {
        return artistDAO.getAllArtists();
    }
    
    public Artist updateArtist(String artistId, String name, String bio, Name relatedName, String genre) {
        Artist artist = getArtist(artistId);
        
        artist.setName(name);
        artist.setBio(bio);
        
        artistDAO.updateArtist(artist);
        
        if (relatedName != null) {
            artist.getRelatedNames().add(relatedName);
            artistDAO.addRelatedName(artistId, relatedName);
        }
        
        if (genre != null) {
            artist.getGenres().add(genre);
            artistDAO.addGenre(artistId, genre);
        }
        
        return artist;
    }

    ArtistIdentifier getArtistIdentifier(String id) {
        Artist artist = getArtist(id);
        return new ArtistIdentifier(artist);
    }
    
    public Set<ArtistIdentifier> search(String query) {
        return artistDAO.search(query);
    }
    
    /**
     * Retrieves the Artist associated with the current session.
     * @return 
     *  Detailed Artist object, or null if none found.
     */
    public Artist getSessionArtist() {
        // get the artist from HTTPSession
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        Artist artist = (Artist) session.getAttribute("artist");
        return artist;
    }
    
    /**
     * Sets the Artist associated with the current session.
     * @param artist
     *  The Artist to associate with this session.
     */
    public void setSessionArtist(Artist artist) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("artist", artist);
    }
    
}
