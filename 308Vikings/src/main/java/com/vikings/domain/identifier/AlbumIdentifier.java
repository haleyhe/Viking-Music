package com.vikings.domain.identifier;

import com.vikings.domain.Album;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Represents a handle to an Album.
 */
public class AlbumIdentifier implements Serializable {
    String id;
    String name;
    List<ArtistIdentifier> artists;
    
    public AlbumIdentifier() {
    }
    
    public AlbumIdentifier(String albumId) {
        this.id = albumId;
    }
    
    public AlbumIdentifier(String id, String name, List<ArtistIdentifier> artists) {
        this.id = id;
        this.name = name;
        this.artists = artists;
    }

    public AlbumIdentifier(Album album) {
        this.id = album.getId();
        this.artists = album.getArtists();
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlbumIdentifier other = (AlbumIdentifier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
