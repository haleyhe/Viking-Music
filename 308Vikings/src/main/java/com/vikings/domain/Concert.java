package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Concert implements Serializable {
    String id;
    Date date;
    Venue venue;
    String ticketingUrl;
    List<Artist> artists;
    
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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    
}
