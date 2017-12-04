package com.vikings.dao.mapper;

import com.vikings.domain.Address;
import com.vikings.domain.Payment;
import com.vikings.domain.PaymentSummary;
import com.vikings.domain.RevenueSummary;
import com.vikings.domain.Statistics;
import java.util.Date;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for PaymentDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/PaymentMapper.xml
 */
public interface PaymentMapper {
    
    public void addAddress(Address address);
    
    public void addPayment(Payment payment);
    
    public void linkPaymentToUser(@Param("userId") String userId, @Param("cardNumber") String cardNumber);
    
    public void unlinkPaymentForUser(@Param("userId") String userId);
    
    public Payment getPaymentForUser(String userId);
    
    public void recordMonthlyPayments(Set<PaymentSummary> payments);
    
    public void recordMonthlyRevenue(Set<RevenueSummary> revenues);
    
    public Set<RevenueSummary> getRevenue(Date month);
    
    public Set<PaymentSummary> getPayments(Date month);
    
    public Set<PaymentSummary> getArtistPayments(@Param("artistId") String artistId, @Param("month") Date month);
    
    public Statistics getStatistics();
    
}

