/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikings.domain.request;

import com.vikings.domain.Name;

public class UpdateArtistRequest {
    private String name;
    private String bio;
    private Name relatedName;
    private String genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Name getRelatedName() {
        return relatedName;
    }

    public void setRelatedName(Name relatedName) {
        this.relatedName = relatedName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
    
}
