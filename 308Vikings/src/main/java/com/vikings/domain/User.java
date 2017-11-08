package com.vikings.domain;

import com.vikings.domain.identifier.UserIdentifier;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a standard User in the Spotify system.
 */
public class User implements Serializable {
    String id;
    String username;
    String password;
    String email;
    Date dateOfBirth;
    String zip;
    Payment payment;
    boolean premium;
    boolean admin;
    UserMusic userMusic;
    String facebookId;
    
    public User() {
        // defaults
        this.premium = false;
        this.admin = false;
    }
    
    public User(String id) {
        this.id = id;
        
        // other defaults
        this.premium = false;
        this.admin = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public UserMusic getUserMusic() {
        return userMusic;
    }

    public void setUserMusic(UserMusic userMusic) {
        this.userMusic = userMusic;
    }
    
    public UserIdentifier toUserIdentifier() {
        return new UserIdentifier(this.id, this.username);
    }
    
}
