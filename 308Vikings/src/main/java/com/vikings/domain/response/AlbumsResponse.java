package com.vikings.domain.response;

import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.List;

public class AlbumsResponse {
    List<AlbumIdentifier> albums;
    
    public AlbumsResponse() {
    }
    
    public AlbumsResponse(List<AlbumIdentifier> albums) {
        this.albums = albums;
    }

    public List<AlbumIdentifier> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumIdentifier> albums) {
        this.albums = albums;
    }
    
}
