package com.vikings.dao.mapper;

import com.vikings.domain.Address;
import com.vikings.domain.Payment;
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
    
}

