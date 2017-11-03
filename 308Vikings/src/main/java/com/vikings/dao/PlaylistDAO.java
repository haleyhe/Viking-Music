package com.vikings.dao;

import com.vikings.dao.mapper.PlaylistMapper;
import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Album;
import com.vikings.domain.Playlist;
import com.vikings.domain.PlaylistSong;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for Playlist and Playlist Page actions
 */
@Repository
public class PlaylistDAO {
    
    @Autowired
    PlaylistMapper playlistMapper;
    
    @Autowired
    SongMapper songMapper;
    
    /**
     * Creates a new, empty Playlist with the given parameters.
     * @param playlist 
     *  The template Playlist object.
     */
    public void createPlaylist(Playlist playlist) {
        playlistMapper.createPlaylist(playlist);
    }
    
    /**
     * Gets information for a single Playlist.
     * @param id
     *  The ID of the desired Playlist.
     * @return 
     *  The detailed Playlist object.
     */
    public Playlist getPlaylist(String id) {
        Playlist playlist = playlistMapper.getPlaylist(id);
        List<PlaylistSong> songs = songMapper.getSongsForPlaylist(id);
        Collections.sort(songs);
        playlist.setSongs(songs);
        return playlist;
    }
    
    /**
     * Adds the given Song to the playlist with the given ID.
     * @param playlistId
     *  ID of the Playlist to append the Song to.
     * @param song 
     *  PlaylistSong, which should include the song ID, date added, and
     *  track number.
     */
    public void addSongToPlaylist(String playlistId, PlaylistSong song) {
        playlistMapper.addSongToPlaylist(playlistId, song);
    }
    
    /**
     * Updates basic information of the Playlist wit the given ID.
     * @param playlist
     *  Playlist object containing the desired Playlist ID.
     *  If non-null, the following attributes will be overwritten with the contents
     *  of the given Playlist object:
     *  - Name
     *  - Description
     *  - PubliclyVisible
     */
    public void updatePlaylist(Playlist playlist) {
        playlistMapper.updatePlaylist(playlist);
    }
    
}
