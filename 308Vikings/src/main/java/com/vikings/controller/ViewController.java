package com.vikings.controller;

import com.vikings.domain.User;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for retrieving page Views.
 */
@Controller
public class ViewController {
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView getStartupPage() {
	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true); 
        User user = (User) session.getAttribute("user");
        
        ModelAndView model = new ModelAndView();
        if (user == null) {
            model.setViewName("startup");
        } else {
            System.out.println("Browse Page is set");
            model.setViewName("browse");
        }
	return model;
    }
}
