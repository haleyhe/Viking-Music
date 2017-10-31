package com.vikings.manager;

import com.vikings.dao.UserMusicDAO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for User Music preference actions
 * (e.g. music library, music history)
 */
@Service
public class UserMusicManager {
    
    @Autowired
    UserMusicDAO userMusicDAO;
    
    /**
     * Marks song as played for the given user.
     * @param userId
     *  User ID.
     * @param songId
     *  Song ID.
     * @param clicked 
     *  True if the user clicked "Play" to initialize the song,
     *  false if song began playing automatically.
     */
    public void markSongAsPlayedForUser(String userId, String songId, boolean clicked) {
        Date timePlayed = new java.util.Date();
        userMusicDAO.markSongAsPlayedForUser(userId, songId, clicked, timePlayed);  
    }
    
}
