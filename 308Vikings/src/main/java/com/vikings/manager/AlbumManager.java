package com.vikings.manager;

import com.vikings.dao.AlbumDAO;
import com.vikings.domain.Album;
import java.util.HashMap;
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
    
    public Album getAlbum(String id) {
        Album album = albums.get(id);
        if (album == null) {
            album = albumDAO.getAlbum(id);
            albums.put(id, album);
        }
        return album;
    }
    
}
