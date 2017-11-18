package com.vikings.domain.response;

import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.List;

public class SearchResponse {
    List<Song> songs;
    List<AlbumIdentifier> albums;
    List<ArtistIdentifier> artists;
    List<PlaylistIdentifier> playlists;
    
    public SearchResponse() {
    }

    public SearchResponse(List<Song> songs, List<AlbumIdentifier> albums, List<ArtistIdentifier> artists, List<PlaylistIdentifier> playlists) {
        this.songs = songs;
        this.albums = albums;
        this.artists = artists;
        this.playlists = playlists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<AlbumIdentifier> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumIdentifier> albums) {
        this.albums = albums;
    }

    public List<ArtistIdentifier> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistIdentifier> artists) {
        this.artists = artists;
    }

    public List<PlaylistIdentifier> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistIdentifier> playlists) {
        this.playlists = playlists;
    }
    
}
