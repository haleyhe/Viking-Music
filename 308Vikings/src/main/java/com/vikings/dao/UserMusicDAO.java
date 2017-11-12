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
     *  ID of the Song., 
     * @param dateAdded 
     *  Timestamp of when the user added the song to their library.
     */
    public void saveSong(String userId, String songId, Date dateAdded) {
        userMusicMapper.saveSong(userId, songId, dateAdded);
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
          * @param dateAdded 
     *  Timestamp of when the user added the album to their library.
     */
    public void saveAlbum(String userId, String albumId, Date dateAdded) {
        userMusicMapper.saveAlbum(userId, albumId, dateAdded);
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
     * @param dateAdded 
     *  Timestamp of when the user added the artist to their library.
     */
    public void followArtist(String userId, String artistId, Date dateAdded) {
        userMusicMapper.followArtist(userId, artistId, dateAdded);
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
    
    /**
     * Marks the given Playlist as followed for the given User.
     * @param userId
     *  ID of the User.
     * @param playlistId 
     *  ID of the Playlist.
     * @param dateAdded
     *  Timestamp of when the user added the playlist to their library.
     */
    public void followPlaylist(String userId, String playlistId, Date dateAdded) {
        userMusicMapper.followPlaylist(userId, playlistId, dateAdded);
    }
    
    /**
     * Marks the given Playlist as unfollowed for the given User.
     * @param userId
     *  ID of the User.
     * @param playlistId 
     *  ID of the Playlist.
     */
    public void unfollowPlaylist(String userId, String playlistId) {
        userMusicMapper.unfollowPlaylist(userId, playlistId);
    }
    
}
