package com.vikings.controller;

import com.vikings.domain.Playlist;
import com.vikings.domain.User;
import com.vikings.domain.requests.JsonResponse;
import com.vikings.manager.FileManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.UserAccountManager;
import com.vikings.manager.UserMusicManager;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for Playlist and Playlist Page actions
 * (creating, editing)
 */
@Controller
public class PlaylistController {
    
    @Autowired
    PlaylistManager playlistManager;
    
    @Autowired
    FileManager fileManager;
    
    @Autowired
    UserAccountManager userAccountManager;
    
    @Autowired
    UserMusicManager userMusicManager;
    
    /**
     * !!! THIS WILL LITERALLY ONLY WORK ON BRYAN'S MACHINE      !!!
     * !!! DUE TO FILEPATH SHENANIGANS THAT I HAVEN'T SOLVED YET !!!
     * !!! The playlist will create in the database correctly,   !!!
     * !!! but image saving will have issues.                    !!!
     * 
     * Makes a new Playlist with the given parameters
     * (created by the User in the current session),
     * marks the Creator as a follower,
     * and attempts to upload the given image thumbnail.
     * @param name
     *  Name of playlist
     * @param description
     *  Description of playlist
     * @param publiclyVisible
     *  Whether the playlist should be visible to others besides creator
     * @param thumbnail
     *  Thumbnail image file.
     * @return 
     *  JsonResponse object indicating success or failure.
     */
    @RequestMapping(method=RequestMethod.POST, value="/Playlist/createPlaylist")
    public @ResponseBody JsonResponse createPlaylist(
            @RequestParam("thumbnail") MultipartFile thumbnail,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("publiclyVisible") boolean publiclyVisible) {
        User sessionUser = userAccountManager.getSessionUser();
        
        if (sessionUser == null) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }
        
        // create the playlist
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.setPubliclyVisible(publiclyVisible);
        playlist.setCreator(sessionUser.toUserIdentifier());
        
        String playlistId = playlistManager.createPlaylist(playlist);
        
        // mark the creator as a follower
        Date creationDate = new java.util.Date();
        userMusicManager.followPlaylist(sessionUser.getId(), playlistId, creationDate);
        userMusicManager.addPlaylistToLibrarySession(sessionUser, playlistId, creationDate);
        
        // attempt to upload the thumbnail
        if (fileManager.uploadPlaylistThumbnail(thumbnail, playlistId)) {
            return new JsonResponse(true);
        } else {
            return new JsonResponse(false, System.getProperty("error.Playlist.thumbnailUploadFail"));
        }
    }
    
}
