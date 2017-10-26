package com.vikings.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a credit card for Premium payment.
 */
public class Payment implements Serializable {
    String cardNumber;
    Name name;
    String cvv;
    Date expirationDate;
    CreditCardCompany creditCardCompany;
    Address billingAddress;
    String phoneNum;
    
    public Payment() {
        
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CreditCardCompany getCreditCardCompany() {
        return creditCardCompany;
    }

    public void setCreditCardCompany(CreditCardCompany creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
}
