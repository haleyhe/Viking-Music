package com.vikings.controller;

import com.vikings.domain.Song;
import com.vikings.domain.User;
import com.vikings.domain.requests.IdRequest;
import com.vikings.domain.requests.JsonResponse;
import com.vikings.domain.requests.MarkSongAsPlayedForUserRequest;
import com.vikings.manager.SongManager;
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
     * @param songId
     * id of the song that the user will add to their library
     * @return 
     *  JsonResponse indicating success or error.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/saveSong", consumes="application/json")
    public @ResponseBody JsonResponse saveSong(@RequestBody IdRequest idReq) {
        User user = userAccountManager.getSessionUser();
        String songId = idReq.getId();
        Date dateAdded = new Date();
        JsonResponse json; //new JsonResponse(true, songId.getId());
        
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
     * @param songId
     * id of the song that the user will remove from their library
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
        return json;    }
    

}
