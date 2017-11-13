/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain.requests;

import java.util.HashMap;

/**
 *
 * @author HH
 */
public abstract class PageResponse {
    HashMap<String,Boolean> savedSongs;

    public HashMap<String, Boolean> getSavedSongs() {
        return savedSongs;
    }

    public void setSavedSongs(HashMap<String, Boolean> savedSongs) {
        this.savedSongs = savedSongs;
    }
}
