package com.vikings.domain.response;

import java.util.Map;

public abstract class PageResponse {
    Map<String,Boolean> savedSongs;
    String error;
    
    public PageResponse() {
    }
    
    public PageResponse(String error) {
        this.error = error;
    }
    
    public Map<String, Boolean> getSavedSongs() {
        return savedSongs;
    }

    public void setSavedSongs(Map<String, Boolean> savedSongs) {
        this.savedSongs = savedSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
