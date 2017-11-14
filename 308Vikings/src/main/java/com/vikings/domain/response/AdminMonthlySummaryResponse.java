package com.vikings.domain.response;

import com.vikings.domain.PaymentSummary;
import com.vikings.domain.RevenueSummary;
import java.util.Set;

public class AdminMonthlySummaryResponse {
    Set<RevenueSummary> revenue;
    Set<PaymentSummary> payments;
    
    public AdminMonthlySummaryResponse() {
        
    }
    
    public AdminMonthlySummaryResponse(Set<RevenueSummary> revenue, Set<PaymentSummary> payments) {
        this.revenue = revenue;
        this.payments = payments;
    }

    public Set<RevenueSummary> getRevenue() {
        return revenue;
    }

    public void setRevenue(Set<RevenueSummary> revenue) {
        this.revenue = revenue;
    }

    public Set<PaymentSummary> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentSummary> payments) {
        this.payments = payments;
    }
    
    
}
