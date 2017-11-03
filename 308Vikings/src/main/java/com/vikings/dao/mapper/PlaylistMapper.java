package com.vikings.dao.mapper;

import com.vikings.domain.Playlist;
import com.vikings.domain.PlaylistSong;
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
    
}

