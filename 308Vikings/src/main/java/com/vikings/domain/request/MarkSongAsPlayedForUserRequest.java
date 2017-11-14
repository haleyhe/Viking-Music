package com.vikings.domain.request;

/**
 * Object for HTTP markSongAsPlayedForUser request.
 */
public class MarkSongAsPlayedForUserRequest {
    String songId;
    boolean clicked;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
}
