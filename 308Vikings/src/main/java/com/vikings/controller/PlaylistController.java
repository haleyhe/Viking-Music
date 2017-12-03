package com.vikings.controller;

import com.vikings.domain.Playlist;
import com.vikings.domain.User;
import com.vikings.domain.identifier.PlaylistIdentifier;
import com.vikings.domain.request.AddPlaylistSongRequest;
import com.vikings.domain.response.JsonResponse;
import com.vikings.domain.response.PlaylistPageResponse;
import com.vikings.domain.request.RemovePlaylistSongRequest;
import com.vikings.domain.response.PlaylistsResponse;
import com.vikings.manager.FileManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.UserAccountManager;
import com.vikings.manager.UserMusicManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            @RequestParam(value="thumbnail", required= false) MultipartFile thumbnail,
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
        if (thumbnail != null) {
            if (!fileManager.uploadPlaylistThumbnail(thumbnail, playlistId)) {
               return new JsonResponse(false, System.getProperty("error.Playlist.thumbnailUploadFail"));
            }
        }
        JsonResponse result = new JsonResponse(true);
        result.setError(playlistId);
        return result;
    }

    @RequestMapping(method=RequestMethod.POST, value="/Playlist/updatePlaylist", consumes = {"multipart/form-data"})
    public @ResponseBody JsonResponse updatePlaylist(
            @RequestParam(value="thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("id") String id) {
        User sessionUser = userAccountManager.getSessionUser();
        if (sessionUser == null) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }

        Playlist playlist = new Playlist(id, name, description);
        playlist.setPubliclyVisible(true);
        if (!playlistManager.updatePlaylist(playlist)) {
             return new JsonResponse(false, System.getProperty("error.Playlist.badPlaylistUpdate"));
        }
        if (thumbnail != null) {
            System.out.println("Detected a new thumbnail");
            if (!fileManager.uploadPlaylistThumbnail(thumbnail, id)) {
                return new JsonResponse(false, System.getProperty("error.Playlist.thumbnailUploadFail"));
            }
        } else {
            System.out.println("No thumbnail");
        }
     
        return new JsonResponse(true);
    }

    /* Fake method to test shit */
    @RequestMapping(method=RequestMethod.GET, value="/Playlist/getAllPlaylists")
    public @ResponseBody PlaylistsResponse getAllPlaylists() {
        List<PlaylistIdentifier> playlistIdents= new ArrayList();
        playlistIdents.add(playlistManager.getPlaylistIdentifier("c793668a-6c14-4188-8f51-b34c96ace7af"));
        return new PlaylistsResponse(playlistIdents);
    }

    @RequestMapping(method=RequestMethod.GET, value="/Playlist/getPlaylistPageDetails")
    public @ResponseBody PlaylistPageResponse getPlaylistPageDetails(@RequestParam("id") String playlistId) {
        Playlist playlist = playlistManager.getPlaylist(playlistId);
        if (playlist == null) {
             return new PlaylistPageResponse(System.getProperty("error.Playlist.noSuchPlaylist"));
        }
        boolean following = userMusicManager.isFollowingPlaylist(playlist);
        HashMap<String,Boolean> savedSongs = userMusicManager.findSavedSongList(playlist);
        int totalDuration = playlistManager.getPlaylistDuration(playlist);
        int numSongs = playlist.getSongs().size();
        return new PlaylistPageResponse(playlist, following, savedSongs, numSongs, totalDuration);
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
     * Checks to see if the logged in user is the playlist creator
     * @param playlistId
     * if of the playlist that will be changed
     * @return
     * True if the logged in user is the creator, false otherwise
     */
    private boolean isUserPlaylistCreator (String playlistId) {
        User user = userAccountManager.getSessionUser();
        Playlist playlist = playlistManager.getPlaylist(playlistId);
        String creatorId = playlist.getCreator().getId();
        return creatorId.equals(user.getId());
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Playlist/getPlaylistsByCreator")
    public @ResponseBody PlaylistsResponse getPlaylistsByCreator(@RequestParam("id") String id) {
        return new PlaylistsResponse(playlistManager.getPlaylistsByCreator(id));
    }
}
