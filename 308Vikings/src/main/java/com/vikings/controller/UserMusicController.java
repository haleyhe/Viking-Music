package com.vikings.controller;

import com.vikings.domain.User;
import com.vikings.domain.requests.MarkSongAsPlayedForUserRequest;
import com.vikings.manager.UserMusicManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controller for User Music preference actions
 * (e.g. music library, music history)
 */
@Controller
public class UserMusicController {
    
    @Autowired
    UserMusicManager userMusicManager;
    
    /**
     * Validates and registers the given User.
     * @param request
     *  MarkSongAsPlayedForUserRequest object containing:
     *      - the user ID,
     *      - the songIdentifier,
     *      - boolean true if the user clicked "Play" to initialize the song,
     *      - false if song began playing automatically.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserMusic/markSongAsPlayedForUser")
    public void markSongAsPlayedForUser(@RequestBody MarkSongAsPlayedForUserRequest request) {
        // get the user from HTTPSession
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            // update the history saved in the session
            if (request.isClicked())
                user.getUserMusic().getRecentlyPlayed().add(request.getSongIdentifier());
            
            user.getUserMusic().getHistory().add(request.getSongIdentifier());
        }
        
        userMusicManager.markSongAsPlayedForUser(request.getUserId(), request.getSongIdentifier().getId(), request.isClicked());
    }

}
