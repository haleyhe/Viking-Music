package com.vikings.dao.mapper;

import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.SongIdentifier;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for UserMusicDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/UserMusicMapper.xml
 */
public interface UserMusicMapper {
    
    public void markSongAsPlayedForUser(@Param("userId") String userId, @Param("songId") String songId, @Param("clicked") boolean clicked, @Param("datePlayed") Date datePlayed);
    
    public void saveSong(@Param("userId") String userId, @Param("songId") String songId);
    
    public void unsaveSong(@Param("userId") String userId, @Param("songId") String songId);
    
    public void saveAlbum(@Param("userId") String userId, @Param("albumId") String albumId);
    
    public void unsaveAlbum(@Param("userId") String userId, @Param("albumId") String albumId);
    
    public void followArtist(@Param("userId") String userId, @Param("artistId") String artistId);
    
    public void unfollowArtist(@Param("userId") String userId, @Param("artistId") String artistId);
    
    public void followPlaylist(@Param("userId") String userId, @Param("playlistId") String playlistId);
    
    public void unfollowPlaylist(@Param("userId") String userId, @Param("playlistId") String playlistId);
    
    public List<SongIdentifier> getSavedSongs(String userId);
    
    public List<AlbumIdentifier> getSavedAlbums(String userId);
    
    public List<ArtistIdentifier> getFollowedArtists(String userId);
    
    public List<SongIdentifier> getHistory(String userId);
    
    public List<SongIdentifier> getRecentlyPlayed(String userId);
    
}

