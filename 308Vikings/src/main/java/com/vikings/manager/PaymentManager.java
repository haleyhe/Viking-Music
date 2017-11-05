package com.vikings.manager;

import com.vikings.dao.PaymentDAO;
import com.vikings.domain.Payment;
import com.vikings.domain.User;
import com.vikings.util.InputChecker;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Manager for User Payment actions
 * (e.g. adding Payment for a User).
 */
@Service
public class PaymentManager {
    
    @Autowired
    PaymentDAO paymentDAO;
    
    /**
     * Checks the given payment and, if valid, links the Payment to the user
     * with the given ID.
     * @param userId
     *  ID of the User to link the payment to.
     * 
     * 
     * @param payment
     *  Payment information to validate and add.
     * @return 
     *  True if successful, false if the payment method is invalid.
     */
    public boolean addPaymentForUser(String userId, Payment payment) {
        boolean valid = isValidPayment(payment);
        if (valid) {
            paymentDAO.addPaymentForUser(userId, payment);
        }
        return valid;
    }
    
    /**
     * Validates the given Payment by checking the expiration date
     * and validating the card number with the Luhn algorithm.
     * @param payment
     *  Detailed Payment object.
     * @return 
     *  true if valid, false otherwise.
     */
    private boolean isValidPayment(Payment payment) {
        if (payment.getCardNumber() == null)
            return false;
        
        if (payment.getExpirationDate().before(new java.util.Date()))
            return false;
        
        // luhn algorithm check
        System.out.println("Doing the luhn algo");
        int sum = 0;
        int numDigits = payment.getCardNumber().length();
        boolean parity = false;
        for (int i = numDigits -1; i >= 0; i--) {
            int digit = Character.getNumericValue(payment.getCardNumber().charAt(i));
            if (parity) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
                sum += digit;
                parity = !parity;
            }
        }
        return sum % 10 == 0;
    }
    
}
