package com.vikings.domain.library;

import com.vikings.domain.identifier.PlaylistIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LibraryPlaylist implements Serializable, Comparable<LibraryPlaylist> {
    private Date dateAdded;
    private PlaylistIdentifier playlistIdentifier;

    public LibraryPlaylist() {
    }

    public LibraryPlaylist(PlaylistIdentifier playlistIdentifier) {
        this.playlistIdentifier = playlistIdentifier;
    }

    public LibraryPlaylist(PlaylistIdentifier playlistIdentifier, Date dateAdded) {
        this.dateAdded = dateAdded;
        this.playlistIdentifier = playlistIdentifier;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public PlaylistIdentifier getPlaylistIdentifier() {
        return playlistIdentifier;
    }

    public void setPlaylistIdentifier(PlaylistIdentifier playlistIdentifier) {
        this.playlistIdentifier = playlistIdentifier;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.playlistIdentifier);
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
        final LibraryPlaylist other = (LibraryPlaylist) obj;
        if (!Objects.equals(this.playlistIdentifier, other.playlistIdentifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(LibraryPlaylist o) {
        if (this.dateAdded == null || o.getDateAdded() == null)
            return 0;
        return dateAdded.compareTo(o.getDateAdded());
    }
    
    
}
