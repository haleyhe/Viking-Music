package com.vikings.domain;

import java.io.Serializable;

public class Statistics implements Serializable {
    int numSongs;
    int numArtists;
    int numAlbums;
    int numSongPlays;
    int numUsers;
    int numPremiumUsers;
    double totalPayments;
    double totalRevenue;

    public Statistics() {
        
    }

    public int getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }

    public int getNumArtists() {
        return numArtists;
    }

    public void setNumArtists(int numArtists) {
        this.numArtists = numArtists;
    }

    public int getNumAlbums() {
        return numAlbums;
    }

    public void setNumAlbums(int numAlbums) {
        this.numAlbums = numAlbums;
    }

    public int getNumSongPlays() {
        return numSongPlays;
    }

    public void setNumSongPlays(int numSongPlays) {
        this.numSongPlays = numSongPlays;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public int getNumPremiumUsers() {
        return numPremiumUsers;
    }

    public void setNumPremiumUsers(int numPremiumUsers) {
        this.numPremiumUsers = numPremiumUsers;
    }

    public double getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(double totalPayments) {
        this.totalPayments = totalPayments;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
}
