package com.vikings.manager;

import com.vikings.dao.UserMusicDAO;
import com.vikings.domain.Album;
import com.vikings.domain.Artist;
import com.vikings.domain.library.LibraryArtist;
import com.vikings.domain.library.LibraryAlbum;
import com.vikings.domain.library.LibraryPlaylist;
import com.vikings.domain.library.LibrarySong;
import com.vikings.domain.Playlist;
import com.vikings.domain.PlaylistSong;
import com.vikings.domain.Song;
import com.vikings.domain.User;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for User Music preference actions
 * (e.g. music library, music history)
 */
@Service
public class UserMusicManager {
    
    @Autowired
    UserMusicDAO userMusicDAO;
    
    @Autowired
    SongManager songManager;
    
    @Autowired
    AlbumManager albumManager;
    
    @Autowired
    ArtistManager artistManager;
    
    @Autowired
    PlaylistManager playlistManager;
    
    @Autowired
    UserAccountManager userAccountManager;
    
    /**
     * Marks song as played for the given user.
     * @param userId
     *  User ID.
     * @param songId
     *  Song ID.
     * @param clicked 
     *  True if the user clicked "Play" to initialize the song,
     *  false if song began playing automatically.
     */
    public void markSongAsPlayedForUser(String userId, String songId, boolean clicked) {
        Date timePlayed = new java.util.Date();
        userMusicDAO.markSongAsPlayedForUser(userId, songId, clicked, timePlayed);  
    }
    
    /**
     * Marks the given Song as saved for the given User.
     * @param userId
     *  ID of the User.
     * @param songId 
     *  ID of the Song.
          * @param dateAdded 
     *  Timestamp of when the user added the song to their library.
     */
    public void saveSong(String userId, String songId, Date dateAdded) {
        userMusicDAO.saveSong(userId, songId, dateAdded);

    }
    
    public boolean addSongToLibrarySession(User user, String songId, Date dateAdded) {
        Set<LibrarySong> savedSongs = user.getUserMusic().getSavedSongs();
        LibrarySong libSong = new LibrarySong(songManager.getSong(songId), dateAdded);
        return savedSongs.add(libSong);
    }
    
    /**
     * Marks the given Song as unsaved for the given User.
     * @param userId
     *  ID of the User.
     * @param songId 
     *  ID of the Song.
     */
    public void unsaveSong(String userId, String songId) {
        userMusicDAO.unsaveSong(userId, songId);
    }
       
    public boolean removeSongFromLibrarySession(User user, String songId) {
        Set<LibrarySong> savedSongs = user.getUserMusic().getSavedSongs();
        Song song = new Song(songId);
        LibrarySong libSong = new LibrarySong(song);
        return savedSongs.remove(libSong);
    }
    
    /**
     * Marks the given Album as saved for the given User.
     * @param userId
     *  ID of the User.
     * @param albumId 
     *  ID of the Album.
          * @param dateAdded 
     *  Timestamp of when the user added the album to their library.
     */
    public void saveAlbum(String userId, String albumId, Date dateAdded) {
        userMusicDAO.saveAlbum(userId, albumId, dateAdded);
    }
    
    public boolean addAlbumToLibrarySession(User user, String albumId, Date dateAdded) {
        Set<LibraryAlbum> savedAlbums = user.getUserMusic().getSavedAlbums();
        LibraryAlbum libAlbum = new LibraryAlbum(albumManager.getAlbumIdentifier(albumId), dateAdded);
        return savedAlbums.add(libAlbum);
    }
    
    /**
     * Marks the given Album as unsaved for the given User.
     * @param userId
     *  ID of the User.
     * @param albumId 
     *  ID of the Album.
     */
    public void unsaveAlbum(String userId, String albumId) {
        userMusicDAO.unsaveAlbum(userId, albumId);
    }
    
    public boolean removeAlbumFromLibrarySession(User user, String albumId) {
        Set<LibraryAlbum> savedAlbums = user.getUserMusic().getSavedAlbums();
        AlbumIdentifier albumIdentifier = new AlbumIdentifier(albumId);
        LibraryAlbum libAlbum = new LibraryAlbum(albumIdentifier);
        return savedAlbums.remove(libAlbum);
    }
    
    public boolean hasSavedAlbum(AlbumIdentifier albumIdentifier) {
        User user = userAccountManager.getSessionUser();
        if (user != null) {
           LibraryAlbum libAlbum = new LibraryAlbum(albumIdentifier);
           return user.getUserMusic().getSavedAlbums().contains(libAlbum);
        }
        return false;
    }
    
    /**
     * Marks the given Artist as followed for the given User.
     * @param userId
     *  ID of the User.
     * @param artistId 
     *  ID of the Artist.
     * @param dateAdded 
     *  Timestamp of when the user added the artist to their library.
     */
    public void followArtist(String userId, String artistId, Date dateAdded) {
        userMusicDAO.followArtist(userId, artistId, dateAdded);
    }
    
    public boolean addArtistToLibrarySession(User user, String artistId, Date dateAdded) {
        Set<LibraryArtist> followedArtists = user.getUserMusic().getFollowedArtists();
        LibraryArtist libArtist = new LibraryArtist(artistManager.getArtistIdentifier(artistId), dateAdded);
        return followedArtists.add(libArtist);
    }

    /**
     * Marks the given Artist as unfollowed for the given User.
     * @param userId
     *  ID of the User.
     * @param artistId 
     *  ID of the Artist.
     */
    public void unfollowArtist(String userId, String artistId) {
        userMusicDAO.unfollowArtist(userId, artistId);
    }
    
    public boolean isFollowingArtist(ArtistIdentifier artistIdentifier) {
        User user = userAccountManager.getSessionUser();
        if (user != null) {
            LibraryArtist libArtist = new LibraryArtist(artistIdentifier);
           return user.getUserMusic().getFollowedArtists().contains(libArtist);
        }
        return false;
    }
    
    public boolean removeArtistFromLibrarySession(User user, String artistId) {
        Set<LibraryArtist> followedArtists = user.getUserMusic().getFollowedArtists();
        ArtistIdentifier artistIdentifier = new ArtistIdentifier(artistId);
        LibraryArtist libArtist = new LibraryArtist(artistIdentifier);
        return followedArtists.remove(libArtist);
    }
    
    /**
     * Marks the given Playlist as followed for the given User.
     * @param userId
     *  ID of the User.
     * @param playlistId 
     *  ID of the Playlist.
     */
    public void followPlaylist(String userId, String playlistId, Date dateAdded) {
        userMusicDAO.followPlaylist(userId, playlistId, dateAdded);
    }
    
    public boolean addPlaylistToLibrarySession(User user, String playlistId, Date dateAdded) {
        Set<LibraryPlaylist> followedPlaylists = user.getUserMusic().getFollowedPlaylists();
        LibraryPlaylist libPlaylist = new LibraryPlaylist(playlistManager.getPlaylistIdentifier(playlistId), dateAdded);
        playlistManager.followPlaylist(playlistId);
        return followedPlaylists.add(libPlaylist);
    }
    
    /**
     * Marks the given Playlist as unfollowed for the given User.
     * @param userId
     *  ID of the User.
     * @param playlistId 
     *  ID of the Playlist.
     */
    public void unfollowPlaylist(String userId, String playlistId) {
        userMusicDAO.unfollowPlaylist(userId, playlistId);
    }
    
    public boolean removePlaylistFromLibrarySession(User user, String playlistId) {
        Set<LibraryPlaylist> followedPlaylists = user.getUserMusic().getFollowedPlaylists();
        PlaylistIdentifier playlistIdentifier = new PlaylistIdentifier(playlistId);
        LibraryPlaylist libPlaylist = new LibraryPlaylist(playlistIdentifier);
        playlistManager.unfollowPlaylist(playlistId);
        return followedPlaylists.remove(libPlaylist);
    }
    
    public boolean isFollowingPlaylist(Playlist playlist) {
        User user = userAccountManager.getSessionUser();
        if (user != null) {
            PlaylistIdentifier playlistIdent = new PlaylistIdentifier(playlist);
            LibraryPlaylist libPlaylist = new LibraryPlaylist(playlistIdent);
           return user.getUserMusic().getFollowedPlaylists().contains(libPlaylist);
        }
        return false;
    }
    
    public HashMap<String,Boolean> findSavedSongList(Album album) {
        User user = userAccountManager.getSessionUser();
        if (user == null) {
           return null;
        }
        HashMap<String,Boolean> savedSongs = new HashMap<>();
        List<Song> songs = album.getSongs();
        for (Song s: songs) {
           LibrarySong librarySong = new LibrarySong(s);
           Boolean saved = user.getUserMusic().getSavedSongs().contains(librarySong);
           savedSongs.put(s.getId(), saved);
        }
        return savedSongs;
    }
    
    public HashMap<String,Boolean>findSavedSongList(Playlist playlist) {
        User user = userAccountManager.getSessionUser();
        HashMap<String,Boolean> savedSongs = new HashMap<>();
        List<PlaylistSong> songList = playlist.getSongs();
        if (user == null) {
            return null;
        }
        for (PlaylistSong ps: songList) {
            Song s = new Song (ps.getId());
            LibrarySong librarySong = new LibrarySong(s);
            Boolean saved = user.getUserMusic().getSavedSongs().contains(librarySong);
            savedSongs.put(ps.getId(), saved);
        }
        return savedSongs;
    }
    
    public List<ArtistIdentifier> getFavoriteArtistsForSessionUser() {
        int ARTIST_FOLLOWED_SCORE = 10;
        int ARTIST_SAVED_CONTENT_SCORE = 2;
        int ARTIST_HISTORY_SCORE = 1;
        
        User user = userAccountManager.getSessionUser();
        if (user == null)
            return null;
        Set<ArtistIdentifier> artists = new HashSet();
        Map <ArtistIdentifier, Integer> artistRankings = new HashMap();
        for (LibraryArtist artist : user.getUserMusic().getFollowedArtists()) {
            int count = 0;
            if (artistRankings.containsKey(artist.getArtistIdentifier())) {
                count = artistRankings.get(artist.getArtistIdentifier());
            }
            artistRankings.put(artist.getArtistIdentifier(), count + ARTIST_FOLLOWED_SCORE);
        }
        for (LibrarySong song : user.getUserMusic().getSavedSongs()) {
            for (ArtistIdentifier artist : song.getSong().getArtists()) {
                int count = 0;
                if (artistRankings.containsKey(artist)) {
                    count = artistRankings.get(artist);
                }
                artistRankings.put(artist, count + ARTIST_SAVED_CONTENT_SCORE);
            }
        }
        for (LibraryAlbum album : user.getUserMusic().getSavedAlbums()) {
            for (ArtistIdentifier artist : album.getAlbumIdentifier().getArtists()) {
                int count = 0;
                if (artistRankings.containsKey(artist)) {
                    count = artistRankings.get(artist);
                }
                artistRankings.put(artist, count + ARTIST_SAVED_CONTENT_SCORE);
            }
        }
        for (Song song : user.getUserMusic().getHistory()) {
            for (ArtistIdentifier artist : song.getArtists()) {
                int count = 0;
                if (artistRankings.containsKey(artist)) {
                    count = artistRankings.get(artist);
                }
                artistRankings.put(artist, count + ARTIST_HISTORY_SCORE);
            }
        }
        List<Map.Entry<ArtistIdentifier,Integer>> sortedArtists = new ArrayList<>(artistRankings.entrySet());
        Collections.sort(sortedArtists,
                new Comparator<Map.Entry<ArtistIdentifier,Integer>>() {
                    @Override
                    public int compare(Map.Entry<ArtistIdentifier,Integer> a, Map.Entry<ArtistIdentifier,Integer> b) {
                        return Integer.compare(b.getValue(), a.getValue());
                    }
                }
        );
        List<ArtistIdentifier> result = new ArrayList<>();
        for (Map.Entry<ArtistIdentifier,Integer> artist : sortedArtists) {
            result.add(artist.getKey());
        }
        return result;
    }
    
    public HashMap<String,Boolean> findSavedSongList(List<Song> songs) {
        User user = userAccountManager.getSessionUser();
        if (user == null) {
           return null;
        }
        HashMap<String,Boolean> savedSongs = new HashMap<>();
        for (Song s: songs) {
           LibrarySong librarySong = new LibrarySong(s);
           Boolean saved = user.getUserMusic().getSavedSongs().contains(librarySong);
           savedSongs.put(s.getId(), saved);
        }
        return savedSongs;
    }
}
