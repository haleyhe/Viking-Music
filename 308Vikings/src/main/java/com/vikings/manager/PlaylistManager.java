package com.vikings.manager;

import com.vikings.dao.PlaylistDAO;
import com.vikings.domain.Playlist;
import com.vikings.domain.PlaylistSong;
import com.vikings.domain.Song;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for Playlist and Playlist Page actions
 */
@Service
public class PlaylistManager {
    
    @Autowired
    PlaylistDAO playlistDAO;
    
    Map<String, Playlist> playlists;
    
    public PlaylistManager() {
        this.playlists = new HashMap<String, Playlist>();
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
        
        playlists.put(id, playlist);
        
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
        Playlist playlist = playlists.get(id);
        if (playlist == null) {
            playlist = playlistDAO.getPlaylist(id);
            playlists.put(id, playlist);
        }
        return playlist;
    }
    
    /**
     * Appends the given song to the Playlist with the given ID.
     * @param playlistId
     *  The ID of the desired Playlist.
     * @param song 
     *  The detailed Song object (this should be obtained from SongManager).
     */
    public void addSongToPlaylist(String playlistId, Song song) {
        Playlist playlist = getPlaylist(playlistId);
        PlaylistSong playlistSong = new PlaylistSong(song);
        Date dateAdded = new java.util.Date();
        int playlistTrackNumber = playlist.getSongs().size() + 1;
        playlistSong.setDateAdded(dateAdded);
        playlistSong.setPlaylistTrackNumber(playlistTrackNumber);
        playlist.getSongs().add(playlistSong);
        
        playlistDAO.addSongToPlaylist(playlistId, playlistSong);
    }
    
}
