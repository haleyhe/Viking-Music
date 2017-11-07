package com.vikings.dao;

import com.vikings.dao.mapper.AlbumMapper;
import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Album;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for Album and Album Page actions
 */
@Repository
public class AlbumDAO {
    
    @Autowired
    AlbumMapper albumMapper;
    
    @Autowired
    SongMapper songMapper;
    
    /**
     * Gets information for a single Album.
     * @param id
     *  The ID of the desired Album.
     * @return 
     *  The detailed Album object.
     */
    public Album getAlbum(String id) {
        Album album = albumMapper.getAlbum(id);
        List<Song> songs = songMapper.getSongsForAlbum(id);
        Collections.sort(songs);
        album.setSongs(songs);
        return album;
    }
    
    /**
     * Returns the 25 most recent albums on the service, by release date.
     * @return 
     *  List of AlbumIdentifiers for recent Albums.
     */
    public List<AlbumIdentifier> getRecentAlbums() {
        return albumMapper.getRecentAlbums();
    }
}
