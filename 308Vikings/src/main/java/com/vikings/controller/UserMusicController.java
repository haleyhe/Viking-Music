package com.vikings.controller;

import com.vikings.domain.Song;
import com.vikings.domain.User;
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
    
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/saveSong")
    public @ResponseBody JsonResponse saveSong(@RequestBody String songId) {
        User user = userAccountManager.getSessionUser();
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
    
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/unsaveSong")
    public @ResponseBody JsonResponse unsaveSong(@RequestBody String songId) {
        User user = userAccountManager.getSessionUser();
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
