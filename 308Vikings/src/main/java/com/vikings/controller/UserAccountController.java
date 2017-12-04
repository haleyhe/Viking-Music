package com.vikings.controller;

import com.vikings.domain.Payment;
import com.vikings.domain.User;
import com.vikings.domain.response.JsonResponse;
import com.vikings.domain.request.LoginRequest;
import com.vikings.manager.PaymentManager;
import com.vikings.manager.UserAccountManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class UserAccountController {
    
    @Autowired
    UserAccountManager userAccountManager;
      
    @Autowired
    PaymentManager paymentManager;
    
    /**
     * Validates and registers the given User.
     * @param newUser
     *  The new User.
     * @return 
     *  True if success, false if error (user exists).
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserAccount/registerUser")
    public @ResponseBody JsonResponse registerUser(@RequestBody User newUser) {
        boolean exists = userAccountManager.userExists(newUser);
        if (!exists) {
            userAccountManager.registerUser(newUser);
            return new JsonResponse(true); 
        } else {
            return new JsonResponse(false, System.getProperty("error.UserAccount.userExists"));
        }
    }
    
    /**
     * Attempts to log in with the given username and password, adding
     * to HttpSession if success.
     * @param loginRequest
     *  LoginRequest object containing the username and password.
     * @return 
     *  true if User found and added to session, false if not found.
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserAccount/processLogin")
    public @ResponseBody JsonResponse processLogin(@RequestBody LoginRequest loginRequest) {
        User loginUser = new User();
        loginUser.setUsername(loginRequest.getUsername());
        loginUser.setPassword(loginRequest.getPassword());
        
        User user = userAccountManager.processLogin(loginUser);
        if (user != null) {
            userAccountManager.setSessionUser(user);
            return new JsonResponse(true);
        } else {
            return new JsonResponse(false, System.getProperty("error.UserAccount.invalidLogin"));
        }
    }
    
    /**
     * Gets the User from HttpSession.
     * @return 
     *  User information, or null if not found.
     */
    @RequestMapping(method=RequestMethod.GET, value="/UserAccount/getSessionUser")
    public @ResponseBody User getSessionUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        User user = (User) session.getAttribute("user");
        return user;
    }
       
    /**
     * Logs the user out and redirect them to the startup page
     *
     * @return User information, or null if not found.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/UserAccount/logout")
    public @ResponseBody JsonResponse logout() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.invalidate();
        return new JsonResponse(true);
    }
    
    /**
     * Updates the User with the given ID with any non-null
     * attributes in the given User object.
     * @param updatedUser
     *  User object containing the correct ID and
     *  attributes to update.
     * @return 
     *  true if success, false otherwise (invalid parameters).
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserAccount/updateUserProfile")
    public @ResponseBody JsonResponse updateUserProfile(@RequestBody User updatedUser) {
        User sessionUser = userAccountManager.getSessionUser();
        if (sessionUser == null | !sessionUser.getId().equals(updatedUser.getId())) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }
        if (!userAccountManager.checkUserAlreadyExist(updatedUser)) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.emailTaken"));
        }
        if (userAccountManager.updateUserProfile(updatedUser)) {
            if (updatedUser.getUsername() != null)
                sessionUser.setUsername(updatedUser.getUsername());
            if (updatedUser.getEmail() != null)
                sessionUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getDateOfBirth() != null)
                sessionUser.setDateOfBirth(updatedUser.getDateOfBirth());
            if (updatedUser.getZip() != null)
                sessionUser.setZip(updatedUser.getZip());
            if (updatedUser.getFacebookId() != null)
                sessionUser.setFacebookId(updatedUser.getFacebookId());
            return new JsonResponse(true);
        } else {
            return new JsonResponse (false, System.getProperty("error.UserAccount.profileUpdateFail"));
        }  
    }
    
    /**
     * Processes the user's credit card and upgrades them to premium
     * @param payment
     * User's payment information
     * @return
     * 
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserAccount/upgrade")
    public @ResponseBody JsonResponse upgradeToPremium(@RequestBody Payment payment) {
        User user = userAccountManager.getSessionUser();
        if (user == null) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }
        
        if (paymentManager.addPaymentForUser(user.getId(), payment)) {
            userAccountManager.makeUserPremium(user, true);
            user.setPremium(true);
            return new JsonResponse(true);
        } else {
            return new JsonResponse(false, System.getProperty("error.UserAccount.upgradeFail"));
        }
    }
    
    /**
     * Downgrades an account from premium to general user
     * @return
     * 
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserAccount/downgrade")
    public @ResponseBody JsonResponse upgradeToPremium() {
        User user = userAccountManager.getSessionUser();
        paymentManager.unlinkPaymentForUser(user.getId());
        userAccountManager.makeUserPremium(user, false);
        return new JsonResponse(true);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/UserAccount/getPayment")
    public @ResponseBody Payment getPayment() {
        User user = userAccountManager.getSessionUser();;
        Payment payment = paymentManager.getPaymentForUser(user.getId());
        payment.setCvv(null);
        String cardNum = payment.getCardNumber();
        cardNum = "XXXX-XXXX-XXXX-" + cardNum.substring(12);
        payment.setCardNumber(cardNum);
        String phone = payment.getPhoneNum();
        phone = "(" + phone.substring(0,3) + ") " + phone.substring(3,6) + "-" + phone.substring(6);
        payment.setPhoneNum(phone);
        return payment;
    }
    
}
