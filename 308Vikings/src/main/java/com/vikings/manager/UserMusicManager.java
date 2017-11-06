package com.vikings.manager;

import com.vikings.dao.UserMusicDAO;
import com.vikings.domain.LibrarySong;
import com.vikings.domain.Song;
import com.vikings.domain.User;
import java.util.Date;
import java.util.Set;
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
    
    @Autowired
    SongManager songManager;
    
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
    
    /**
     * Marks the given Song as saved for the given User.
     * @param userId
     *  ID of the User.
     * @param songId 
     *  ID of the Song.
     */
    public void saveSong(String userId, String songId, Date dateAdded) {
        userMusicDAO.saveSong(userId, songId, dateAdded);

    }
    
    /**
     * Marks the given Song as unsaved for the given User.
     * @param userId
     *  ID of the User.
     * @param songId 
     *  ID of the Song.
     */
    public void unsaveSong(String userId, String songId) {
        userMusicDAO.unsaveSong(userId, songId);
    }
    
    /**
     * Marks the given Album as saved for the given User.
     * @param userId
     *  ID of the User.
     * @param albumId 
     *  ID of the Album.
     */
    public void saveAlbum(String userId, String albumId, Date dateAdded) {
        userMusicDAO.saveAlbum(userId, albumId, dateAdded);
    }
    
    /**
     * Marks the given Album as unsaved for the given User.
     * @param userId
     *  ID of the User.
     * @param albumId 
     *  ID of the Album.
     */
    public void unsaveAlbum(String userId, String albumId) {
        userMusicDAO.unsaveAlbum(userId, albumId);
    }
    
    /**
     * Marks the given Artist as followed for the given User.
     * @param userId
     *  ID of the User.
     * @param artistId 
     *  ID of the Artist.
     */
    public void followArtist(String userId, String artistId, Date dateAdded) {
        userMusicDAO.followArtist(userId, artistId, dateAdded);
    }
    
    /**
     * Marks the given Artist as unfollowed for the given User.
     * @param userId
     *  ID of the User.
     * @param artistId 
     *  ID of the Artist.
     */
    public void unfollowArtist(String userId, String artistId) {
        userMusicDAO.unfollowArtist(userId, artistId);
    }
    
    /**
     * Marks the given Playlist as followed for the given User.
     * @param userId
     *  ID of the User.
     * @param playlistId 
     *  ID of the Playlist.
     */
    public void followPlaylist(String userId, String playlistId) {
        userMusicDAO.followPlaylist(userId, playlistId);
    }
    
    /**
     * Marks the given Playlist as unfollowed for the given User.
     * @param userId
     *  ID of the User.
     * @param playlistId 
     *  ID of the Playlist.
     */
    public void unfollowPlaylist(String userId, String playlistId) {
        userMusicDAO.unfollowPlaylist(userId, playlistId);
    }
    
    public boolean addSongToLibrarySession(User user, String songId, Date dateAdded) {
        Set<LibrarySong> savedSongs = user.getUserMusic().getSavedSongs();
        LibrarySong libSong = new LibrarySong(songManager.getSong(songId), dateAdded);
        return savedSongs.add(libSong);
    }
    
    public boolean removeSongFromLibrarySession(User user, String songId) {
        Set<LibrarySong> savedSongs = user.getUserMusic().getSavedSongs();
        Song song = new Song(songId);
        LibrarySong libSong = new LibrarySong(song);
        return savedSongs.remove(libSong);
    }
}
