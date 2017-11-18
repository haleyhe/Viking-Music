package com.vikings.dao.mapper;

import com.vikings.domain.Playlist;
import com.vikings.domain.PlaylistSong;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for PlaylistDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/PlaylistMapper.xml
 */
public interface PlaylistMapper {
    
    public void createPlaylist(Playlist playlist);
    
    public Playlist getPlaylist(String id);
    
    public void addSongToPlaylist(@Param("playlistId") String playlistId, @Param("song") PlaylistSong song);
    
    public void updatePlaylist(Playlist playlist);
    
    public void removeSongFromPlaylist(@Param("playlistId") String playlistId, @Param("trackNum") int trackNum);
    
    public void updatePlaylistTrackNumbersAfterRemove(@Param("playlistId") String playlistId, @Param("removedTrackNum") int removedTrackNum);
    
    public PlaylistSong getPlaylistSong(@Param("playlistId") String playlistId, @Param("trackNum") int trackNum);
    
    public void updatePlaylistTrackNumbersBeforeInsert(@Param("playlistId") String playlistId, @Param("newTrackNum") int newTrackNum);
    
    public List<Playlist> getAdminCuratedPlaylists();
    
    public List<PlaylistIdentifier> search(@Param("query") String query, @Param("limit") Integer limit);
}

