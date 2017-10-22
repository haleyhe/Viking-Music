package com.vikings.controller;

import com.vikings.domain.User;
import com.vikings.manager.UserAccountManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    // Returns the View for the signup page.
    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        // We have to add an empty User object to the
        // model so that the signup page can populate
        // its fields with signup informaiton.
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "signup";
    }
    
    // Returns the View for the sign-in page.
    @GetMapping("/signin")
    public String getSigninPage(Model model) {
        User loginUser = new User();
        model.addAttribute("loginUser", loginUser);
        return "signin";
    }
    
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        // get User from the session
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        User user = (User) session.getAttribute("user");
        
        model.addAttribute("user", user);
        return "profile";
    }
    
    // Attempts to register the given User.
    @RequestMapping(method=RequestMethod.POST, value="/registerUser")
    public String registerUser(@ModelAttribute("newUser") User newUser, Model model) {
        // @TODO
        // No validation yet.
        
        userAccountManager.registerUser(newUser);
        
        // redirect to the signin page
        return getSigninPage(model);
    }
    
    // Attempts to log in with the desired User,
    // adding to HttpSession if success.
    @RequestMapping(method=RequestMethod.GET, value="/processLogin")
    public String processLogin(@ModelAttribute("loginUser") User loginUser, Model model) {
        // see if we get a User
        User user = userAccountManager.processLogin(loginUser);
        
        if (user != null) {
            // add user to the session
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("user", user);
            
            // redirect to user profile page
            return getProfilePage(model);
        } else {
            // reload the signin page with error message
            model.addAttribute("errorMessage", "Account not found.");
            return getSigninPage(model);
        }
    }
    
}
