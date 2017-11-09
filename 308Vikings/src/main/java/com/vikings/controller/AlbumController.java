package com.vikings.controller;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.manager.AlbumManager;
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
