package com.vikings.controller;

import com.vikings.domain.LibraryArtist;
import com.vikings.domain.LibraryAlbum;
import com.vikings.domain.LibraryPlaylist;
import com.vikings.domain.LibrarySong;
import com.vikings.domain.Song;
import com.vikings.domain.User;
import com.vikings.domain.identifier.PlaylistIdentifier;
import com.vikings.domain.requests.IdRequest;
import com.vikings.domain.requests.JsonResponse;
import com.vikings.domain.requests.MarkSongAsPlayedForUserRequest;
import com.vikings.manager.SongManager;
import com.vikings.manager.UserAccountManager;
import com.vikings.manager.UserMusicManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for User Music preference actions
 * (e.g. music library, music history)
 */
@Controller
public class UserMusicController {
    
    @Autowired
    UserMusicManager userMusicManager;
    
    @Autowired
    SongManager songManager;
    
    @Autowired
    UserAccountManager userAccountManager;
    
    @RequestMapping(method=RequestMethod.GET, value="/UserMusic/library/songs")
    public @ResponseBody List<LibrarySong> getLibrarySongs() {
        User user = userAccountManager.getSessionUser();
        List<LibrarySong> savedSongList = new ArrayList(user.getUserMusic().getSavedSongs());
        Collections.sort(savedSongList);
        return savedSongList;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/UserMusic/library/albums")
    public @ResponseBody List<LibraryAlbum> getLibraryAlbums() {
        User user = userAccountManager.getSessionUser();
        List<LibraryAlbum> savedAlbumList = new ArrayList(user.getUserMusic().getSavedAlbums());
        Collections.sort(savedAlbumList);
        return savedAlbumList;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/UserMusic/library/artists")
    public @ResponseBody List<LibraryArtist> getFollowedArtist() {
        User user = userAccountManager.getSessionUser();
        List<LibraryArtist> followedArtistList = new ArrayList(user.getUserMusic().getFollowedArtists());
        Collections.sort(followedArtistList);
        return followedArtistList;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/UserMusic/library/playlist")
    public @ResponseBody List<LibraryPlaylist> getFollowedPlaylist() {
        User user = userAccountManager.getSessionUser();
        List<LibraryPlaylist> followedPlaylistList = new ArrayList(user.getUserMusic().getFollowedPlaylists());
        Collections.sort(followedPlaylistList);
        return followedPlaylistList;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/UserMusic/library/recentlyPlayed")
    public @ResponseBody List<Song> getRecentlyPlayed() {
        User user = userAccountManager.getSessionUser();
        return user.getUserMusic().getRecentlyPlayed();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/UserMusic/history")
    public @ResponseBody List<Song> getHistory() {
        User user = userAccountManager.getSessionUser();
        return user.getUserMusic().getHistory();
    }
    

    /**
     * Validates and registers the given User.
     * @param request
     *  MarkSongAsPlayedForUserRequest object containing:
     *      - the song ID
     *      - boolean true if the user clicked "Play" to initialize the song,
     *      - false if song began playing automatically.
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/markSongAsPlayedForUser")
    public @ResponseBody JsonResponse markSongAsPlayedForUser(@RequestBody MarkSongAsPlayedForUserRequest request) {
        User user = userAccountManager.getSessionUser();
        
        if (user != null) {
            Song song = songManager.getSong(request.getSongId());
            // update the history saved in the session
            if (request.isClicked())
                user.getUserMusic().getRecentlyPlayed().add(song);
            
            user.getUserMusic().getHistory().add(song);
            
            userMusicManager.markSongAsPlayedForUser(user.getId(), request.getSongId(), request.isClicked());
            
            return new JsonResponse(true);
        } else {
            return new JsonResponse(false, "User session expired.");
        }
          
    }
    
     /**
     * Adds a song to an user's library
     * @param idReq
     * json container for the id of the song that the user will add to their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/saveSong", consumes="application/json")
    public @ResponseBody JsonResponse saveSong(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String songId = idReq.getId();
        Date dateAdded = new Date();
        JsonResponse json; 
        
        userMusicManager.saveSong(user.getId(), songId, dateAdded);
        
        if (userMusicManager.addSongToLibrarySession(user, songId, dateAdded)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This song is already in your library"); //probably need better error checking than this
        }
        return json;
    }
    
    
    /**
     * Removes a song from an user's library
     * @param idReq
     * json container for the id of the song that the user will remove from their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/unsaveSong")
    public @ResponseBody JsonResponse unsaveSong(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String songId = idReq.getId();
       JsonResponse json;
        
        userMusicManager.unsaveSong(user.getId(), songId);
        
        if (userMusicManager.removeSongFromLibrarySession(user, songId)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This song is not in your library"); //probably need better error checking than this
        }
        return json;    
    }
    
     /**
     * Adds an album to an user's library
     * @param idReq
     * json container for the id of the album that the user will add to their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/saveAlbum")
    public @ResponseBody JsonResponse saveAlbum(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String albumId = idReq.getId();
        Date dateAdded = new Date();
        JsonResponse json; 
        
        userMusicManager.saveAlbum(user.getId(), albumId, dateAdded);
        
        if (userMusicManager.addAlbumToLibrarySession(user, albumId, dateAdded)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This album is already in your library"); //probably need better error checking than this
        }
        return json;
    }
    
    
    /**
     * Removes an album from an user's library
     * @param idReq
     * json container for the id of the album that the user will remove from their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/unsaveAlbum")
    public @ResponseBody JsonResponse unsaveAlbum(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String albumId = idReq.getId();
       JsonResponse json;
        
        userMusicManager.unsaveAlbum(user.getId(), albumId);
        
        if (userMusicManager.removeAlbumFromLibrarySession(user, albumId)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This album is not in your library"); //probably need better error checking than this
        }
        return json;    
    }
    
    /**
     * Adds an artist to an user's library
     * @param idReq
     * json container for the id of the artist that the user will add to their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/followArtist")
    public @ResponseBody JsonResponse followArtist(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String artistId = idReq.getId();
        Date dateAdded = new Date();
        JsonResponse json; 
        
        userMusicManager.followArtist(user.getId(), artistId, dateAdded);
        
        if (userMusicManager.addArtistToLibrarySession(user, artistId, dateAdded)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This artist is already in your library"); //probably need better error checking than this
        }
        return json;
    }
    
        /**
     * Removes an artist from an user's library
     * @param idReq
     * json container for the id of the artist that the user will remove from their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/unfollowArtist")
    public @ResponseBody JsonResponse unfollowArtist(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String artistId = idReq.getId();
       JsonResponse json;
        
        userMusicManager.unfollowArtist(user.getId(), artistId);
        
        if (userMusicManager.removeArtistFromLibrarySession(user, artistId)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This artist is not in your library"); //probably need better error checking than this
        }
        return json;    
    }
    
    /**
     * Adds an playlist to an user's library
     * @param idReq
     * json container for the id of the playlist that the user will add to their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/followPlaylist")
    public @ResponseBody JsonResponse followPlaylist(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String playlistId = idReq.getId();
        Date dateAdded = new Date();
        JsonResponse json; 
        
        userMusicManager.followPlaylist(user.getId(), playlistId, dateAdded);
        
        if (userMusicManager.addPlaylistToLibrarySession(user, playlistId, dateAdded)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This playlist is already in your library"); //probably need better error checking than this
        }
        return json;
    }
    
     /**
     * Removes an artist from an user's library
     * @param idReq
     * json container for the id of the artist that the user will remove from their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/unfollowPlaylist")
    public @ResponseBody JsonResponse unfollowPlaylist(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String playlistId = idReq.getId();
       JsonResponse json;
        
        userMusicManager.unfollowPlaylist(user.getId(), playlistId);
        
        if (userMusicManager.removePlaylistFromLibrarySession(user, playlistId)) {
            userAccountManager.setSessionUser(user);
            json = new JsonResponse(true);
        } else {
            json = new JsonResponse(false, "This playlist is not in your library"); //probably need better error checking than this
        }
        return json;    
    }
}
