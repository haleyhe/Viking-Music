package com.vikings.manager;

import com.vikings.dao.AlbumDAO;
import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for Album and Album Page actions
 */
@Service
public class AlbumManager {
    
    @Autowired
    AlbumDAO albumDAO;
    
    Map<String, Album> albums;
    
    public AlbumManager() {
        this.albums = new HashMap<String, Album>();
    }
    
    /**
     * Retrieves Album and adds it to the albums cache.
     * @param id
     *  ID of the desired Album.
     * @return 
     *  Detailed Album object.
     */
    public Album getAlbum(String id) {
        Album album = albums.get(id);
        if (album == null) {
            album = albumDAO.getAlbum(id);
            albums.put(id, album);
        }
        return album;
    }
    
    /**
     * Returns the 25 most recent albums on the service, by release date.
     * @return 
     *  List of AlbumIdentifiers for recent Albums.
     */
    public List<AlbumIdentifier> getRecentAlbums() {
        return albumDAO.getRecentAlbums();
    }
    
}