package com.vikings.domain;

import com.vikings.domain.identifier.UserIdentifier;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a summary of a payment made by a User to Spotify,
 * retrieved from the database.
 */
public class RevenueSummary implements Serializable {
    UserIdentifier user;
    Payment payment;
    Date datePaid;
    double amountPaid;
    
    public RevenueSummary() {
        
    }
    
    public RevenueSummary(UserIdentifier user, Payment payment, Date datePaid, double amountPaid) {
        this.user = user;
        this.payment = payment;
        this.datePaid = datePaid;
        this.amountPaid = amountPaid;
    }

    public UserIdentifier getUser() {
        return user;
    }

    public void setUser(UserIdentifier user) {
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
