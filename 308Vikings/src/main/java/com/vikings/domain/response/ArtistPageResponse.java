package com.vikings.domain.response;

import com.vikings.domain.Concert;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;

public class ArtistPageResponse {
    String id;
    String name;
    String bio;
    List<Song> topSongs;
    boolean isFollowing;
    List<ArtistIdentifier> relatedArtists;
    List<AlbumIdentifier> albums;
    List<Concert> concerts;
 
    public ArtistPageResponse() {
    }
 
    public ArtistPageResponse(String id, String name, String bio, boolean isFollowing, List<Song> topSongs,  List<ArtistIdentifier> 
        relatedArtists,List<AlbumIdentifier> albums, List<Concert> concerts) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.isFollowing = isFollowing;
        this.topSongs = topSongs;
        this.relatedArtists = relatedArtists;
        this.albums = albums;
        this.concerts = concerts;
    }

    public String getId(){
        return id;
    }
    
    public void setId(){
        this.id= id;
    }
    
    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
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
 
    public boolean isFollowing(){
        return isFollowing;
    }
    
    public void setIsFollowing(boolean isFollowing){
        this.isFollowing = isFollowing;
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
