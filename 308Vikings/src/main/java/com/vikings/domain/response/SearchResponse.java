package com.vikings.domain.response;

import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.Set;

public class SearchResponse {
    Set<Song> songs;
    Set<AlbumIdentifier> albums;
    Set<ArtistIdentifier> artists;
    Set<PlaylistIdentifier> playlists;
    
    public SearchResponse() {
    }

    public SearchResponse(Set<Song> songs, Set<AlbumIdentifier> albums, Set<ArtistIdentifier> artists, Set<PlaylistIdentifier> playlists) {
        this.songs = songs;
        this.albums = albums;
        this.artists = artists;
        this.playlists = playlists;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Set<AlbumIdentifier> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumIdentifier> albums) {
        this.albums = albums;
    }

    public Set<ArtistIdentifier> getArtists() {
        return artists;
    }

    public void setArtists(Set<ArtistIdentifier> artists) {
        this.artists = artists;
    }

    public Set<PlaylistIdentifier> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlaylistIdentifier> playlists) {
        this.playlists = playlists;
    }
    
}
