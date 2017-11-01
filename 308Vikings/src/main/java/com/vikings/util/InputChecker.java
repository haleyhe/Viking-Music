package com.vikings.util;

public class InputChecker {
    public static final String EMAIL_VERIFICATION = "^\\S+@\\S+\\.\\S+$";
    public static final String ZIP_VERIFICATION = "\\d{5}";
    public static final String CREDIT_CARD_VERIFICATION = "\\d{16}";
    
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_VERIFICATION);
    } 
    
    public static boolean isValidZip(String zip) {
        return zip.matches(ZIP_VERIFICATION);
    } 
    
    public static boolean isValidCreditCard (String creditCard) {
        if (!creditCard.matches(CREDIT_CARD_VERIFICATION)) {
            return false;
        } 
        //TODO implements Ludh's algorithms
        return true;
    }
}
