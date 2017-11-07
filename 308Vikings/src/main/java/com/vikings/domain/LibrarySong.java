/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author HH
 */
public class LibrarySong implements Serializable, Comparable<LibrarySong> {
    Date dateAdded;
    Song song;
    
    public LibrarySong() {
        
    }

    public LibrarySong(Song song, Date dateAdded) {
        this.dateAdded = dateAdded;
        this.song = song;
    }

    public LibrarySong(Song song) {
        this.song = song;
    }
    
    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        //hash = 79 * hash + Objects.hashCode(this.dateAdded);
        hash = 79 * hash + Objects.hashCode(this.song);
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
        final LibrarySong other = (LibrarySong) obj;
        if (!Objects.equals(this.song, other.song)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(LibrarySong o) {
        if (this.dateAdded == null || o.getDateAdded() == null)
            return 0;
        return dateAdded.compareTo(o.getDateAdded());
    }
    
    
    
}
