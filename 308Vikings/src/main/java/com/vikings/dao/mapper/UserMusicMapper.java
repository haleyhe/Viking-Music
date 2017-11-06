package com.vikings.dao.mapper;

import com.vikings.domain.LibrarySong;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for UserMusicDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/UserMusicMapper.xml
 */
public interface UserMusicMapper {
    
    public void markSongAsPlayedForUser(@Param("userId") String userId, @Param("songId") String songId, @Param("clicked") boolean clicked, @Param("datePlayed") Date datePlayed);
    
    public void saveSong(@Param("userId") String userId, @Param("songId") String songId, @Param("dateAdded") Date dateAdded);
    
    public void unsaveSong(@Param("userId") String userId, @Param("songId") String songId);
    
    public void saveAlbum(@Param("userId") String userId, @Param("albumId") String albumId, @Param("dateAdded") Date dateAdded);
    
    public void unsaveAlbum(@Param("userId") String userId, @Param("albumId") String albumId);
    
    public void followArtist(@Param("userId") String userId, @Param("artistId") String artistId, @Param("dateAdded") Date dateAdded);
    
    public void unfollowArtist(@Param("userId") String userId, @Param("artistId") String artistId);
    
    public void followPlaylist(@Param("userId") String userId, @Param("playlistId") String playlistId);
    
    public void unfollowPlaylist(@Param("userId") String userId, @Param("playlistId") String playlistId);
    
    public Set<LibrarySong> getSavedSongs(String userId);
    
    public Set<AlbumIdentifier> getSavedAlbums(String userId);
    
    public Set<ArtistIdentifier> getFollowedArtists(String userId);
    
    public Set<PlaylistIdentifier> getFollowedPlaylists(String userId);
    
    public List<Song> getHistory(String userId);
    
    public List<Song> getRecentlyPlayed(String userId);
    
}

