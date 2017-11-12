package com.vikings.controller;

import com.vikings.domain.Artist;
import com.vikings.domain.PaymentSummary;
import com.vikings.domain.RevenueSummary;
import com.vikings.domain.requests.AdminMonthlySummaryResponse;
import com.vikings.domain.requests.ArtistMonthlySummaryResponse;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.PaymentManager;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {
    
    @Autowired
    PaymentManager paymentManager;
    
    @Autowired
    ArtistManager artistManager;
    
    /**
     * THIS SHOULD NOT BE CALLED MANUALLY BEYOND DEBUGGING.
     * It is scheduled to run automatically on fixed intervals, so long as
     * the application is running.
     * 
     * Generates revenue summaries (payments from premium users)
     * and payment summaries (payments to artist for song plays)
     * at the beginning of each month.
     *  
     */
    @Scheduled(cron="${cron.Payment.generatePayments}")
    @RequestMapping(method=RequestMethod.GET, value="/Payment/generateMonthlyPayments")
    public void generateMonthlyPayments() {
        paymentManager.generateMonthlyPayments();
        paymentManager.generateMonthlyRevenue();
    }
    
    /**
     * Retrieves the monthly summary for the Admin, including listings of all
     * User revenue and all Artist payments.
     * @param month
     *  Month to see payments for.
     * @return 
     *  Response object containing set of RevenueSummaries and set of PaymentSummaries.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Payment/getAdminMonthlySummary")
    public @ResponseBody AdminMonthlySummaryResponse getAdminMonthlySummary(@RequestParam("month") Date month) {
        Set<RevenueSummary> revenue = paymentManager.getRevenue(month);
        Set<PaymentSummary> payments = paymentManager.getPayments(month);
        return new AdminMonthlySummaryResponse(revenue, payments);
    }
    
    /**
     * Retrieves the monthly summary for the Artist in the session, including detailed information
     * about the royalty amount paid for each Song.
     * @param month
     *  Month to see payments for.
     * @return 
     *  Response object containing set of PaymentSummaries, or null if no artist in session.
     */
    @RequestMapping(method=RequestMethod.POST, value="/Payment/getArtistMonthlySummary")
    public @ResponseBody ArtistMonthlySummaryResponse getArtistMonthlySummary(@RequestBody Date month) {
        Artist artist = artistManager.getSessionArtist();
        if (artist == null)
            return null;
        
        Set<PaymentSummary> payments = paymentManager.getArtistPayments(artist.getId(), month);
        return new ArtistMonthlySummaryResponse(payments);
    }
}
