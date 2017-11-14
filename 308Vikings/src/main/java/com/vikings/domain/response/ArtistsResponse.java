package com.vikings.domain.response;

import com.vikings.domain.identifier.ArtistIdentifier;
import java.util.List;

public class ArtistsResponse {
    List<ArtistIdentifier> artists;
    
    public ArtistsResponse() {
    }

    public ArtistsResponse(List<ArtistIdentifier> artists) {
        this.artists = artists;
    }

    public List<ArtistIdentifier> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistIdentifier> artists) {
        this.artists = artists;
    }
    
}
