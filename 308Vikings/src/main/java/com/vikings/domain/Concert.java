package com.vikings.domain;

import com.vikings.domain.identifier.ArtistIdentifier;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Concert implements Serializable {
    String id;
    Date date;
    Venue venue;
    String ticketingUrl;
    List<ArtistIdentifier> artists;
    
    public Concert() {
        
    }
    
    public Concert(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getTicketingUrl() {
        return ticketingUrl;
    }

    public void setTicketingUrl(String ticketingUrl) {
        this.ticketingUrl = ticketingUrl;
    }

    public List<ArtistIdentifier> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistIdentifier> artists) {
        this.artists = artists;
    }
    
}
