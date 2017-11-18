package com.vikings.dao.mapper;

import com.vikings.domain.PlaylistSong;
import com.vikings.domain.Song;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for SongDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/SongMapper.xml
 */
public interface SongMapper {
    
    public Song getSong(String id);
    
    public List<Song> getSongsForAlbum(String id);
    
    public List<PlaylistSong> getSongsForPlaylist(String id);
    
    public List<Song> getTopSongsForArtist(String id);
    
    public Set<Song> getArtistSongsForPayment(@Param("id") String id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    public List<Song> search(@Param("query") String query, @Param("limit") Integer limit);
}

