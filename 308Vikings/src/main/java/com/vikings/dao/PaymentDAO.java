package com.vikings.dao;

import com.vikings.dao.mapper.PaymentMapper;
import com.vikings.domain.Payment;
import com.vikings.domain.PaymentSummary;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for User Payment actions
 * (e.g. adding Payment for a User).
 */
@Repository
public class PaymentDAO {
    
    @Autowired
    PaymentMapper paymentMapper;
    
    /**
     * Adds a payment and links it to a user.
     * @param userId
     *  User ID.
     * @param payment 
     *  Detailed Payment object (which should be verified beforehand).
     */
    public void addPaymentForUser(String userId, Payment payment) {
        paymentMapper.addAddress(payment.getBillingAddress());
        paymentMapper.addPayment(payment);
        paymentMapper.linkPaymentToUser(userId, payment.getCardNumber());
    }
    
    public void recordMonthlyPayments(List<PaymentSummary> payments) {
        paymentMapper.recordMonthlyPayments(payments);
    }
}
