package com.vikings.domain.response;

import com.vikings.domain.identifier.PlaylistIdentifier;
import java.util.List;

public class PlaylistsResponse {
    List<PlaylistIdentifier> playlists;

    public PlaylistsResponse() {
    }

    public PlaylistsResponse(List<PlaylistIdentifier> albums) {
        this.playlists = albums;
    }

    public List<PlaylistIdentifier> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistIdentifier> playlists) {
        this.playlists = playlists;
    }
    
}
