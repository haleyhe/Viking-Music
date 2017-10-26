package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a summary of a payment made to an Artist,
 * retrieved from the database.
 */
public class PaymentSummary implements Serializable {
    String id;
    String name;
    int numPlays;
    Date datePaid;
    double amountPaid;
    
    public PaymentSummary() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlays() {
        return numPlays;
    }

    public void setNumPlays(int numPlays) {
        this.numPlays = numPlays;
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
