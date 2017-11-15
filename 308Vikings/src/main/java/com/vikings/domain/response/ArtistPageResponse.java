package com.vikings.domain.response;

import com.vikings.domain.Concert;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;

public class ArtistPageResponse {
    String bio;
    List<Song> topSongs;
    List<ArtistIdentifier> relatedArtists;
    List<AlbumIdentifier> albums;
    List<Concert> concerts;
 
    public ArtistPageResponse() {
    }
 
    public ArtistPageResponse(String bio, List<Song> topSongs, List<ArtistIdentifier> 
        relatedArtists,List<AlbumIdentifier> albums, List<Concert> concerts) {
        this.bio = bio;
        this.topSongs = topSongs;
        this.relatedArtists = relatedArtists;
        this.albums = albums;
        this.concerts = concerts;
    }

    public String getBio(){
        return bio;
    }

    public void setBio(String bio){
        this.bio = bio;
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

    public List<Concert> getConcerts(){
        return concerts;
    }

    public void setConcerts(List<Concert> concerts){
        this.concerts = concerts;
    }
}
