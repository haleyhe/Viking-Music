package com.vikings.controller;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.response.AlbumPageResponse;
import com.vikings.domain.response.AlbumsResponse;
import com.vikings.manager.AlbumManager;
import com.vikings.manager.UserAccountManager;
import com.vikings.manager.UserMusicManager;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlbumController {
    
    @Autowired
    AlbumManager albumManager;
    
    @Autowired
    UserMusicManager userMusicManager;
    
    @Autowired
    UserAccountManager userAccountManager;
    
    /**
     * Packages and returns necessary info for Album Page.
     * @param id
     *  Album ID.
     * @return 
     *  Response object containing:
     *  -Album info,
     *  -Related albums,
     *  -Boolean indicating whether the user has saved the Album.
     *  -Map of all of the songs and whether they are saved in the user's library
     *  Or error message if no album found.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Album/getAlbumPageDetails")
    public @ResponseBody AlbumPageResponse getAlbumPageDetails(@RequestParam("id")  String id) {
        Album album = albumManager.getAlbum(id);
        if (album == null) {
            return new AlbumPageResponse(System.getProperty("error.Album.noSuchAlbum"));
        }
        List<AlbumIdentifier> relatedAlbums = albumManager.getAlbumsForArtist(album.getArtists().get(0).getId());
        relatedAlbums.remove(album.toAlbumIdentifier());
        boolean saved = userMusicManager.hasSavedAlbum(album.toAlbumIdentifier());
        HashMap<String,Boolean> savedSongs = userMusicManager.findSavedSongList(album);
        return new AlbumPageResponse(album, relatedAlbums, saved, savedSongs);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Album/getAllAlbums")
    public @ResponseBody AlbumsResponse getAllAlbums() {
        return new AlbumsResponse(albumManager.getAllAlbums()); 
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
