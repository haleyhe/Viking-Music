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
        // check if the user exists
        boolean exists = userAccountManager.userExists(newUser);
        
        JsonResponse json = new JsonResponse();
        if (!exists) {
            userAccountManager.registerUser(newUser);
            json.setSuccess(true);  
        } else {
            json.setSuccess(false);
            json.setError(System.getProperty("error.UserAccount.userExists"));
        }
        return json;
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
        
        // see if we get a User
        User user = userAccountManager.processLogin(loginUser);
        
        JsonResponse json = new JsonResponse();
        if (user != null) {
            userAccountManager.setSessionUser(user);
            json.setSuccess(true);
        } else {
            json.setSuccess(false);
            json.setError(System.getProperty("error.UserAccount.invalidLogin"));
        }
        return json;
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
        
        JsonResponse json = new JsonResponse();
        json.setSuccess(true);
        return json;
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
        if (userAccountManager.updateUserProfile(updatedUser)) {
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
        if (paymentManager.addPaymentForUser(user.getId(), payment)) {
            userAccountManager.makeUserPremium(user, true);
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
        userAccountManager.makeUserPremium(user, false);
        return new JsonResponse(true);
    }
    
    
}
