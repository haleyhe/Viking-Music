package com.vikings.dao;

import com.vikings.dao.mapper.UserMusicMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for User Music preference actions
 * (e.g. saved songs, follows, history)
 */
@Repository
public class UserMusicDAO {
    
    @Autowired
    UserMusicMapper userMusicMapper;
    
    /**
     * Marks the given Song as played for the given User.
     * @param userId
     *  ID of the User.
     * @param songId
     *  ID of the Song.
     * @param clicked
     *  True if User clicked "play" for the Song, false if Song played automatically.
     * @param datePlayed 
     *  Timestamp of song play.
     */
    public void markSongAsPlayedForUser(String userId, String songId, boolean clicked, Date datePlayed) {
        userMusicMapper.markSongAsPlayedForUser(userId, songId, clicked, datePlayed);
    }
    
    /**
     * Marks the given Song as saved for the given User.
     * @param userId
     *  ID of the User.
     * @param songId 
     *  ID of the Song.
     */
    public void saveSong(String userId, String songId) {
        userMusicMapper.saveSong(userId, songId);
    }
    
    /**
     * Marks the given Song as unsaved for the given User.
     * @param userId
     *  ID of the User.
     * @param songId 
     *  ID of the Song.
     */
    public void unsaveSong(String userId, String songId) {
        userMusicMapper.unsaveSong(userId, songId);
    }
    
    /**
     * Marks the given Album as saved for the given User.
     * @param userId
     *  ID of the User.
     * @param albumId 
     *  ID of the Album.
     */
    public void saveAlbum(String userId, String albumId) {
        userMusicMapper.saveAlbum(userId, albumId);
    }
    
    /**
     * Marks the given Album as unsaved for the given User.
     * @param userId
     *  ID of the User.
     * @param albumId 
     *  ID of the Album.
     */
    public void unsaveAlbum(String userId, String albumId) {
        userMusicMapper.unsaveAlbum(userId, albumId);
    }
    
    /**
     * Marks the given Artist as followed for the given User.
     * @param userId
     *  ID of the User.
     * @param artistId 
     *  ID of the Artist.
     */
    public void followArtist(String userId, String artistId) {
        userMusicMapper.followArtist(userId, artistId);
    }
    
    /**
     * Marks the given Artist as unfollowed for the given User.
     * @param userId
     *  ID of the User.
     * @param artistId 
     *  ID of the Artist.
     */
    public void unfollowArtist(String userId, String artistId) {
        userMusicMapper.unfollowArtist(userId, artistId);
    }
    
}
