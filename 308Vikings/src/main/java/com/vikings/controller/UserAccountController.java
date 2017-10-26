package com.vikings.controller;

import com.vikings.domain.User;
import com.vikings.manager.UserAccountManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controller for User Account pages and actions
 * (e.g. sign up, sign in, edit profile)
 */
@Controller
public class UserAccountController {
    
    @Autowired
    UserAccountManager userAccountManager;
    
    // Returns the View for the startup page.
    @GetMapping("/")
    public String getStartupPage() {
        return "startup";
    }
    
    /**
     * Validates and registers the given User.
     * @param newUser
     *  The new User.
     * @return 
     *  True if success, false if error (user exists).
     */
    @RequestMapping(method=RequestMethod.POST, value="/UserAccount/registerUser")
    public @ResponseBody boolean registerUser(@RequestBody User newUser) {
        // check if the user exists
        boolean exists = userAccountManager.userExists(newUser);
        
        if (!exists) {
            userAccountManager.registerUser(newUser);
            return true;  
        } else {
            return false;
        }
    }
    
    /**
     * Attempts to log in with the given username and password, adding
     * to HttpSession if success.
     * @param username
     *  Username of desired User.
     * @param password
     *  Password of desired User.
     * @return 
     *  true if User found and added to session, false if not found.
     */
    @RequestMapping(method=RequestMethod.GET, value="/UserAccount/processLogin")
    public @ResponseBody boolean processLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        
        // see if we get a User
        User user = userAccountManager.processLogin(loginUser);
        
        if (user != null) {
            // add user to the session
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("user", user);
            
            return true;
        } else {
            return false;
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

}