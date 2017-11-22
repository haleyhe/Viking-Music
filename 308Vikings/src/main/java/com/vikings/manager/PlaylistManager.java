package com.vikings.manager;

import com.vikings.dao.PlaylistDAO;
import com.vikings.domain.Playlist;
import com.vikings.domain.PlaylistSong;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistManager {
    
    @Autowired
    PlaylistDAO playlistDAO;
    
    @Autowired
    SongManager songManager;
    
    Map<String, Playlist> playlistCache;
    
    public PlaylistManager() {
        this.playlistCache = new HashMap<>();
    }
    
    /**
     * Creates a new, empty Playlist with the given parameters.
     * @param playlist 
     *  The template Playlist object. The following parameters
     *  should be provided:
     *      -Name
     *      -Creator identifier
     *      -publiclyVisible boolean
     *      -Description (optional)
     * @return
     *  The ID of the created Playlist.
     */
    public String createPlaylist(Playlist playlist) {
        String id = java.util.UUID.randomUUID().toString();
        Date creationDate = new java.util.Date();
        playlist.setId(id);
        playlist.setCreationDate(creationDate);
        playlist.setNumFollowers(0);
        playlist.setSongs(new ArrayList<PlaylistSong>());
        
        playlistDAO.createPlaylist(playlist);
        playlistCache.put(id, playlist);
        return id;
    }
    
    /**
     * Retrieves Playlist and adds it to the Playlist cache.
     * @param id
     *  ID of the desired Playlist.
     * @return 
     *  Detailed Playlist object.
     */
    public Playlist getPlaylist(String id) {
        Playlist playlist = playlistCache.get(id);
        if (playlist == null) {
            playlist = playlistDAO.getPlaylist(id);
            playlistCache.put(id, playlist);
        }
        return playlist;
    }
    
    public PlaylistIdentifier getPlaylistIdentifier(String id) {
        Playlist playlist = getPlaylist(id);
        return new PlaylistIdentifier(playlist);
    }
    
    /**
     * Appends the given song to the end of the Playlist with the given ID.
     * @param playlistId
     *  The ID of the desired Playlist.
     * @param songId 
     *  ID of the Song.
     */
    public void addSongToPlaylist(String playlistId, String songId) {
        Playlist playlist = getPlaylist(playlistId);
        Song song = songManager.getSong(songId);
        PlaylistSong playlistSong = new PlaylistSong(song);
        Date dateAdded = new java.util.Date();
        int playlistTrackNumber = playlist.getSongs().size() + 1;
        playlistSong.setDateAdded(dateAdded);
        playlistSong.setPlaylistTrackNumber(playlistTrackNumber);
        playlist.getSongs().add(playlistSong);
        
        playlistDAO.addSongToPlaylist(playlistId, playlistSong);
    }
    
    /**
     * Removes the track with the given track number from the playlist
     * with the given ID.
     * @param playlistId
     *  ID of the desired Playlist.
     * @param trackNum
     *  Track number of the song to remove.
     * @return 
     *  The updated Playlist.
     */
    public Playlist removeSongFromPlaylist(String playlistId, int trackNum) {
        Playlist playlist = getPlaylist(playlistId);
        playlist.removeSong(trackNum);
        
        playlistDAO.removeSongFromPlaylist(playlistId, trackNum);
        
        return playlist; 
    }
    
    /**
     * Moves the track with the given old track number to the given new position
     * in the playlist with the given ID.
     * @param playlistId
     *  ID of the desired Playlist.
     * @param oldTrackNum
     *  Track number of the song to move.
     * @param newTrackNum
     *  The new track number (position) for the song.
     * @return 
     *  The updated Playlist.
     */
    public Playlist moveSongInPlaylist(String playlistId, int oldTrackNum, int newTrackNum) {
        Playlist playlist = getPlaylist(playlistId);
        playlist.moveSong(oldTrackNum, newTrackNum);
        
        playlistDAO.moveSongInPlaylist(playlistId, oldTrackNum, newTrackNum);
        
        return playlist;    
    }
    
    /**
     * Updates basic information of the Playlist wit the given ID.
     * @param playlist
     *  Playlist object containing the desired Playlist ID.
     *  If non-null, the following attributes will be overwritten with the contents
     *  of the given Playlist object:
     *  - Name
     *  - Description
     *  The following attributes will be overwritten regardless of if you set them
     *  or not, so make sure this is set to the desired state:
     *  - PubliclyVisible
     */
    public void updatePlaylist(Playlist playlist) {
        if (playlistCache.get(playlist.getId()) != null) {
            Playlist oldPlaylist = playlistCache.get(playlist.getId());
            if (playlist.getName() != null)
                oldPlaylist.setName(playlist.getName());
            if (playlist.getDescription() != null)
                oldPlaylist.setDescription(playlist.getDescription());
                
            oldPlaylist.setPubliclyVisible(playlist.isPubliclyVisible());
        }
        playlistDAO.updatePlaylist(playlist);
    }
    
    /**
     * Returns Playlist information for all Playlists created by admins.
     * These will be the "Genre" playlists on the Browse Page and the Genres &
     * Moods Page.
     * @return 
     *  A list of Playlists with IDs, titles, descriptors, etc.
     */
    public List<Playlist> getAdminCuratedPlaylists() {
        return playlistDAO.getAdminCuratedPlaylists();
    }
    
    public List<PlaylistIdentifier> search(String query, Integer limit) {
        return playlistDAO.search(query, limit);
    }
    
    public int getPlaylistDuration (Playlist playlist) {
        int totalDuration = 0;
        for (Song s : playlist.getSongs()) {
            totalDuration += s.getDuration();
        }
        return totalDuration;
    }
    
   public void followPlaylist (String playlistId) {
       Playlist playlist = getPlaylist(playlistId);
       playlist.setNumFollowers(playlist.getNumFollowers()+1);
   }
   
    public void unfollowPlaylist (String playlistId) {
       Playlist playlist = getPlaylist(playlistId);
       playlist.setNumFollowers(playlist.getNumFollowers()-1);
   }
}
