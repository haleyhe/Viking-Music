package com.vikings.dao.mapper;

import com.vikings.domain.Address;
import com.vikings.domain.Album;
import com.vikings.domain.Artist;
import com.vikings.domain.Concert;
import com.vikings.domain.Song;
import com.vikings.domain.Venue;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for importing data.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/DataImportMapper.xml
 */
public interface DataImportMapper {
    
    public void createArtist(List<Artist> artists);
    
    public void createArtistGenres(List<Artist> artists);
    
    public void createAlbum(List<Album> albums);
    
    public void createAlbumArtists(List<Album> albums);
    
    public void createSong(List<Song> songs);
    
    public void createSongArtists(List<Song> songs);
    
    public void createSongAlbum(List<Song> songs);
    
    public void createAddress(Address address);
    
    public void createVenue(Venue venue);
    
    public void createConcert(Concert concert);
    
    public void createConcertArtists(Concert concert);
    
}