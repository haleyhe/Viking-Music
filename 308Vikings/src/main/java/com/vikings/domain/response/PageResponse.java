package com.vikings.domain.response;

import java.util.HashMap;

public abstract class PageResponse {
    HashMap<String,Boolean> savedSongs;
    String error;
    
    public PageResponse() {
    }
    
    public PageResponse(String error) {
        this.error = error;
    }
    
    public HashMap<String, Boolean> getSavedSongs() {
        return savedSongs;
    }

    public void setSavedSongs(HashMap<String, Boolean> savedSongs) {
        this.savedSongs = savedSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
