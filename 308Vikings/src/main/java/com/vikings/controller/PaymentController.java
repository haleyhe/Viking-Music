package com.vikings.controller;

import com.vikings.manager.PaymentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {
    
    @Autowired
    PaymentManager paymentManager;
    
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
    @RequestMapping(method=RequestMethod.POST, value="/Payment/generateMonthlyPayments")
    public void generateMonthlyPayments() {
        paymentManager.generateMonthlyPayments();
    }
}
