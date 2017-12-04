package com.vikings.manager;

import com.vikings.dao.ArtistDAO;
import com.vikings.dao.PaymentDAO;
import com.vikings.dao.SongDAO;
import com.vikings.dao.UserAccountDAO;
import com.vikings.domain.Artist;
import com.vikings.domain.Payment;
import com.vikings.domain.PaymentSummary;
import com.vikings.domain.RevenueSummary;
import com.vikings.domain.Song;
import com.vikings.domain.Statistics;
import com.vikings.domain.User;
import com.vikings.util.InputChecker;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for User Payment actions
 * (e.g. adding Payment for a User), as well as generating and retrieving
 * Payment and Revenue summaries.
 */
@Service
public class PaymentManager {
    
    static final double MONTHLY_PREMIUM_COST = 5.00;
    
    @Autowired
    PaymentDAO paymentDAO;
    
    @Autowired
    ArtistDAO artistDAO;
    
    @Autowired
    SongDAO songDAO;
    
    @Autowired
    UserAccountDAO userAccountDAO;
    
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
        if (payment == null) {
            return false;
        } 
        
        if (payment.getExpirationDate() == null || payment.getExpirationDate().before(new java.util.Date())) {
            return false;
        }
        
        String cardNum = payment.getCardNumber();
        if (cardNum == null || !InputChecker.isValidCreditCard(cardNum)) {
            return false;
        }
       return checkForLuhn(cardNum);
    }
    
    private boolean checkForLuhn (String cardNum) {
        int sum = 0;
        int numDigits = cardNum.length();
        boolean parity = false;
        for (int i = numDigits -1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNum.charAt(i));
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
    
    public Set<RevenueSummary> getRevenue(Date month) {
        return paymentDAO.getRevenue(month);
    }
    
    public Set<PaymentSummary> getPayments(Date month) {
        return paymentDAO.getPayments(month);
    }
    
    public Set<PaymentSummary> getArtistPayments(String artistId, Date month) {
        return paymentDAO.getArtistPayments(artistId, month);
    }
    
    public void generateMonthlyPayments() {
        Date startDate = getStartDate();
        Date endDate = getEndDate();
        System.out.println("AUTOMATED: Generating payments from " + startDate.toString() + " to " + endDate.toString());
        Set<Artist> artists = artistDAO.getAllArtistsForPayment();
        for (Artist artist : artists) {
            Set<Song> songs = songDAO.getArtistSongsForPayment(artist.getId(), startDate, endDate);
            if (songs.size() > 0) {
                Set<PaymentSummary> payments = new HashSet<>();
                for (Song song : songs) {
                    String id = song.getId();
                    int numPlays = song.getNumPlays();
                    Date datePaid = new java.util.Date();
                    double amountPaid = numPlays * artist.getRoyaltyRate();
                    
                    payments.add(new PaymentSummary(id, numPlays, datePaid, amountPaid));
                }
                paymentDAO.recordMonthlyPayments(payments);
            }
        }
        System.out.println("Done.");
    }
        
    public void generateMonthlyRevenue() {
        System.out.println("AUTOMATED: Generating monthly User Premium revenue.");
        Set<User> users = userAccountDAO.getPremiumUsersForMonthlyRevenue();
        Set<RevenueSummary> revenues = new HashSet<>();
        for (User user : users) {
            if (isValidPayment(user.getPayment())) {
                Date datePaid = new java.util.Date();
                double amountPaid = MONTHLY_PREMIUM_COST;    
                revenues.add(new RevenueSummary(user.toUserIdentifier(), user.getPayment(), datePaid, amountPaid));   
            } else {
                user.setPremium(false);
                userAccountDAO.updateUser(user);
            }
        }
        paymentDAO.recordMonthlyRevenue(revenues);
        System.out.println("Done.");
    }
    
    private Date getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.MONTH, false);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    private Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.MONTH, false);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
    public Statistics getStatistics() {
        return paymentDAO.getStatistics();
    }
    
    public void unlinkPaymentForUser(String userId) {
        paymentDAO.unlinkPaymentForUser(userId);
    } 
    
    public Payment getPaymentForUser(String userId) {
        return paymentDAO.getPaymentForUser(userId);
    }
    
}
