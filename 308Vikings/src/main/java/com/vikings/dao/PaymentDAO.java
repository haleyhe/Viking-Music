package com.vikings.dao;

import com.vikings.dao.mapper.PaymentMapper;
import com.vikings.domain.Payment;
import com.vikings.domain.PaymentSummary;
import com.vikings.domain.RevenueSummary;
import com.vikings.domain.Statistics;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    
    public void unlinkPaymentForUser(String userId) {
        paymentMapper.unlinkPaymentForUser(userId);
    }
    
    public Payment getPaymentForUser(String userId) {
        return paymentMapper.getPaymentForUser(userId);
    }
    
    public void recordMonthlyPayments(Set<PaymentSummary> payments) {
        paymentMapper.recordMonthlyPayments(payments);
    }
    
    public void recordMonthlyRevenue(Set<RevenueSummary> revenues) {
        paymentMapper.recordMonthlyRevenue(revenues);
    }
    
    public Set<RevenueSummary> getRevenue(Date month) {
        return paymentMapper.getRevenue(month);
    }
    
    public Set<PaymentSummary> getPayments(Date month) {
        return paymentMapper.getPayments(month);
    }
    
    public Set<PaymentSummary> getArtistPayments(String artistId, Date month) {
        return paymentMapper.getArtistPayments(artistId, month);
    }
    
    public Statistics getStatistics() {
        return paymentMapper.getStatistics();
    }
}
