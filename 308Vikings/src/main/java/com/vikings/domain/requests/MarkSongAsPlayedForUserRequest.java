package com.vikings.domain.requests;

import com.vikings.domain.identifier.SongIdentifier;

/**
 * Object for HTTP markSongAsPlayedForUser request.
 */
public class MarkSongAsPlayedForUserRequest {
    String userId;
    SongIdentifier songIdentifier;
    boolean clicked;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
