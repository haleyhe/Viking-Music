package com.vikings.manager;

import com.vikings.dao.AlbumDAO;
import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumManager {
    
    @Autowired
    AlbumDAO albumDAO;
    
    Map<String, Album> albumCache;
    
    public AlbumManager() {
        this.albumCache = new HashMap<>();      
    }
    
    /**
     * Retrieves Album and adds it to the albums cache.
     * @param id
     *  ID of the desired Album.
     * @return 
     *  Detailed Album object.
     */
    public Album getAlbum(String id) {
        Album album = albumCache.get(id);
        if (album == null) {
            album = albumDAO.getAlbum(id);
            albumCache.put(id, album);
        }
        return album;
    }
    
    /**
     * Returns albums released with in the past year on the service, by release date.
     * @return 
     *  List of AlbumIdentifiers for recent Albums.
     */
    public List<AlbumIdentifier> getRecentAlbums() {
        return albumDAO.getRecentAlbums();
    }
    
    public List<AlbumIdentifier> getAlbumsForArtist(String id) {
        return albumDAO.getAlbumsForArtist(id);
    }

    public AlbumIdentifier getAlbumIdentifier(String id) {
        Album album = getAlbum(id);
        return new AlbumIdentifier(album);
    }
    
    public Set<AlbumIdentifier> search(String query) {
        return albumDAO.search(query);
    }
    
}
