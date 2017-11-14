/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain.response;

import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;

public class ArtistPageResponse {
    List<Song> topSongs;
    List<ArtistIdentifier> relatedArtists;
    List<AlbumIdentifier> albums;
 
    public ArtistPageResponse() {
    }
 
    public ArtistPageResponse(List<Song> topSongs, List<ArtistIdentifier> 
        relatedArtists,List<AlbumIdentifier> albums) {
        this.topSongs = topSongs;
        this.relatedArtists = relatedArtists;
        this.albums = albums;
    }
    
    public List<Song> getTopSongs(){
        return topSongs;
    }
 
    public void setTopSongs(List<Song> topSongs) {
        this.topSongs = topSongs;
    }
 
    public List<ArtistIdentifier> getRelatedArtists() {
        return relatedArtists;
    }
 
    public void setRelatedArtists(List<ArtistIdentifier> relatedArtists) {
        this.relatedArtists = relatedArtists;
    }
 
    public List<AlbumIdentifier> getAlbums() {
        return albums;
    }
 
    public void setAlbums(List<AlbumIdentifier> albums) {
        this.albums = albums;
    }
}
