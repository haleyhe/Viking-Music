package com.vikings.dao.mapper;

import com.vikings.domain.Album;
import com.vikings.domain.Artist;
import com.vikings.domain.Song;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for importing data.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/DataImportMapper.xml
 */
public interface DataImportMapper {
    
    public void createArtist(Artist artist);
    
    public void createArtistGenres(Artist artist);
    
    public void createAlbum(Album album);
    
    public void createAlbumArtists(Album album);
    
    public void createSong(Song song);
    
    public void createSongArtists(Song song);
    
    public void createSongAlbum(Song song);
    
}