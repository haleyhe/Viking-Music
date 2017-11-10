package com.vikings.controller;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.requests.AlbumPageResponse;
import com.vikings.manager.AlbumManager;
import com.vikings.manager.UserMusicManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for Album and Album Page actions
 */
@Controller
public class AlbumController {
    
    @Autowired
    AlbumManager albumManager;
    
    @Autowired
    UserMusicManager userMusicManager;
    
    /**
     * Packages and returns necessary info for Album Page.
     * @param id
     *  Album ID.
     * @return 
     *  Response object containing:
     *  -Album info,
     *  -Related albums,
     *  -Boolean indicating whether the user has saved the Album.
     * 
     * Or null if no Album found.
     *  
     */
    @RequestMapping(method=RequestMethod.GET, value="/Album/getAlbumPageDetails")
    public @ResponseBody AlbumPageResponse getAlbumPageDetails(@RequestParam("id") String id) {
        Album album = albumManager.getAlbum(id);
        if (album == null)
            return null;
        List<AlbumIdentifier> relatedAlbums = albumManager.getAlbumsForArtist(album.getArtists().get(0).getId());
        relatedAlbums.remove(album.toAlbumIdentifier());
        boolean saved = userMusicManager.hasSavedAlbum(album.toAlbumIdentifier());
        
        return new AlbumPageResponse(album, relatedAlbums, saved);
    }
    
    
    
    /**
     * Gets the Album with the given ID.
     * @param id
     *  ID of the Album.
     * @return 
     *  Album object if found, null otherwise.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Album/getAlbum")
    public @ResponseBody Album getAlbum(@RequestParam("id") String id) {
        return albumManager.getAlbum(id);
    }
    
    /**
     * Returns albums released in the past year on the service, by release date.
     * @return 
     *  List of AlbumIdentifiers for recent Albums.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Album/getRecentAlbums")
    public @ResponseBody List<AlbumIdentifier> getRecentAlbums() {
        return albumManager.getRecentAlbums();
    }
    
    

}
