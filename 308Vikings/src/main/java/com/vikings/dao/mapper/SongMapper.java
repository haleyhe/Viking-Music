package com.vikings.dao.mapper;

import com.vikings.domain.PlaylistSong;
import com.vikings.domain.Song;
import java.util.List;

/**
 * Mapper class for SongDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/SongMapper.xml
 */
public interface SongMapper {
    
    public Song getSong(String id);
    
    public List<Song> getSongsForAlbum(String id);
    
    public List<PlaylistSong> getSongsForPlaylist(String id);
    
}

