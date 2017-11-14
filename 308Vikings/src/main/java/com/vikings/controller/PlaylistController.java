package com.vikings.controller;

import com.vikings.domain.Playlist;
import com.vikings.domain.User;
import com.vikings.domain.requests.AddPlaylistSongRequest;
import com.vikings.domain.requests.JsonResponse;
import com.vikings.domain.requests.RemovePlaylistSongRequest;
import com.vikings.manager.FileManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.UserAccountManager;
import com.vikings.manager.UserMusicManager;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    
    
    /**
        * Adds a song to an user's created playlist
        * @param addSongReq
        * json container for the song and playlist IDs
        * @return 
        *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/Playlist/addSong", consumes="application/json")
    public @ResponseBody JsonResponse addSongToPlaylist(@RequestBody AddPlaylistSongRequest addSongReq) {
        String playlistId = addSongReq.getPlaylistId();
        String songId = addSongReq.getSongId();
        if (!isUserPlaylistCreator(playlistId)) {
            return new JsonResponse (false,System.getProperty("error.Playlist.notCreator"));
        } 
        playlistManager.addSongToPlaylist(playlistId, songId);
        return new JsonResponse(true);
    }
    
    /**
        * Removes a song from an user's created playlist
        * @param remSongReq
        * json container for the playlist id and the track number of the song the creator wants to remove
        * @return 
        *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/Playlist/removeSong", consumes="application/json")
    public @ResponseBody JsonResponse removeSongFromPlaylist(@RequestBody RemovePlaylistSongRequest remSongReq) {
        String playlistId = remSongReq.getPlaylistId();
        int trackNum = remSongReq.getTrackNum();
        if (!isUserPlaylistCreator(playlistId)) {
            return new JsonResponse (false,System.getProperty("error.Playlist.notCreator"));
        } 
        playlistManager.removeSongFromPlaylist(playlistId, trackNum);
        return new JsonResponse(true);
    }
    
    /**
     * Edits the information of the User's create
     * @param playlistId
     * json container for the playlist info that the creator wants to update
     * @return 
     * JsonResponse indicating success or error.
     */
    private boolean isUserPlaylistCreator (String playlistId) {
        User user = userAccountManager.getSessionUser();
        Playlist playlist = playlistManager.getPlaylist(playlistId);
        String creatorId = playlist.getCreator().getId();
        boolean isCreator = creatorId.equals(user.getId());
        System.out.println(isCreator);
        return isCreator;
    }
}
