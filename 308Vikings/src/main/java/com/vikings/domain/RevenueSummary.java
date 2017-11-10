package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a summary of a payment made by a User to Spotify,
 * retrieved from the database.
 */
public class RevenueSummary implements Serializable {
    User user;
    Payment payment;
    Date datePaid;
    double amountPaid;
    
    public RevenueSummary() {
        
    }
    
    public RevenueSummary(User user, Payment payment, Date datePaid, double amountPaid) {
        this.user = user;
        this.payment = payment;
        this.datePaid = datePaid;
        this.amountPaid = amountPaid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
    
}
