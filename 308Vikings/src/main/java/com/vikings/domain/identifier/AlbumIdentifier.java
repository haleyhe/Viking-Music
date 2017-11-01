package com.vikings.domain.identifier;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a handle to an Album.
 */
public class AlbumIdentifier implements Serializable {
    String id;
    String name;
    List<ArtistIdentifier> artists;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArtistIdentifier> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistIdentifier> artists) {
        this.artists = artists;
    }
    
}
