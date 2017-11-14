package com.vikings.domain.response;

import com.vikings.domain.PaymentSummary;
import java.util.Set;

public class ArtistMonthlySummaryResponse {
    Set<PaymentSummary> payments;
    
    public ArtistMonthlySummaryResponse() {
        
    }
    
    public ArtistMonthlySummaryResponse(Set<PaymentSummary> payments) {
        this.payments = payments;
    }

    public Set<PaymentSummary> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentSummary> payments) {
        this.payments = payments;
    }
    
    
}
