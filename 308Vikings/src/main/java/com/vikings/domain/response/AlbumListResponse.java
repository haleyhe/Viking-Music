package com.vikings.domain.response;

import com.vikings.domain.Album;
import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.List;

public class AlbumListResponse {
    Album album;
    List<AlbumIdentifier> relatedAlbums;
    boolean saved;
    
    public AlbumListResponse() {
    }
    
    public AlbumListResponse(Album album, List<AlbumIdentifier> relatedAlbums, boolean saved) {
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
