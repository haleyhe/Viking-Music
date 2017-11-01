package com.vikings.domain.requests;

import com.vikings.domain.identifier.SongIdentifier;

/**
 * Object for HTTP markSongAsPlayedForUser request.
 */
public class MarkSongAsPlayedForUserRequest {
    SongIdentifier songIdentifier;
    boolean clicked;

    public SongIdentifier getSongIdentifier() {
        return songIdentifier;
    }

    public void setSongIdentifier(SongIdentifier songIdentifier) {
        this.songIdentifier = songIdentifier;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
}
