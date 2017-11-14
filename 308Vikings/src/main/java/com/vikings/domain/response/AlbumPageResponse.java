package com.vikings.domain.response;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.HashMap;
import java.util.List;

public class AlbumPageResponse extends PageResponse{
    Album album;
    List<AlbumIdentifier> relatedAlbums;
    boolean saved;
    
    public AlbumPageResponse() {
    }
    
    public AlbumPageResponse(Album album, List<AlbumIdentifier> relatedAlbums, boolean saved) {
        this.album = album;
        this.relatedAlbums = relatedAlbums;
        this.saved = saved;
    }

    public AlbumPageResponse(Album album, List<AlbumIdentifier> relatedAlbums, boolean saved, HashMap<String, Boolean> savedSongs) {
        this.savedSongs = savedSongs;
        this.album = album;
        this.relatedAlbums = relatedAlbums;
        this.saved = saved;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<AlbumIdentifier> getRelatedAlbums() {
        return relatedAlbums;
    }

    public void setRelatedAlbums(List<AlbumIdentifier> relatedAlbums) {
        this.relatedAlbums = relatedAlbums;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    
      
}
