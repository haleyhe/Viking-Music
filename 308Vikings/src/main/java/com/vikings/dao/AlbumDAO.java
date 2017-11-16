package com.vikings.dao;

import com.vikings.dao.mapper.AlbumMapper;
import com.vikings.dao.mapper.SongMapper;
import com.vikings.domain.Album;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumDAO {
    
    @Autowired
    AlbumMapper albumMapper;
    
    @Autowired
    SongMapper songMapper;
    
    public Album getAlbum(String id) {
        Album album = albumMapper.getAlbum(id);
        if (album == null) {
            return null;
        }
        List<Song> songs = songMapper.getSongsForAlbum(id);
        Collections.sort(songs);
        album.setSongs(songs);
        return album;
    }
    
    public List<AlbumIdentifier> getAllAlbums() {
        return albumMapper.getAllAlbums();
    }
    
    public List<AlbumIdentifier> getAlbumsForArtist(String id) {
        return albumMapper.getAlbumsForArtist(id);
    }
    
    public List<AlbumIdentifier> getRecentAlbums() {
        return albumMapper.getRecentAlbums();
    }
    
    public Set<AlbumIdentifier> search(String query) {
        query = "%" + query + "%";
        return albumMapper.search(query);
    }
}
