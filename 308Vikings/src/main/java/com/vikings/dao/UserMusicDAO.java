package com.vikings.dao;

import com.vikings.domain.Artist;
import com.vikings.domain.Song;
import com.vikings.domain.User;
import java.util.Date;
import org.springframework.stereotype.Repository;

/**
 * DAO for User Music preference actions
 * (e.g. saved songs, follows, history)
 */
@Repository
public class UserMusicDAO {
    
    //@TODO
    public void markSongAsPlayedForUser(User user, Song song, boolean clicked, Date datePlayed) {
        //@TODO
    }
    
    //@TODO
    public void saveSong(User user, Song song) {
        //@TODO
    }
    
    //@TODO
    public void unsaveSong(User user, Song song) {
        //@TODO
    }
    
    //@TODO
    public void followArtist(User user, Artist artist) {
        //@TODO
    }
    
    //@TODO
    public void unfollowArtist(User user, Artist artist) {
        //@TODO
    }
    
}
