package com.vikings.controller;

import com.vikings.domain.Artist;
import com.vikings.domain.User;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for retrieving page Views.
 */
@Controller
public class ViewController {
    
    @Autowired
    UserAccountManager userAccountManager;
    
    @Autowired
    ArtistManager artistManager;
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView getStartupPage() {
	User user = userAccountManager.getSessionUser();
        
        ModelAndView model = new ModelAndView();
        if (user == null) {
            model.setViewName("startup");
            model.addObject("user", user);
        } else {
            model.setViewName("browse");
        }
	return model;
    }
    
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public ModelAndView getTestPage() {  
        ModelAndView model = new ModelAndView();
        model.setViewName("test");
	return model;
    }
    
    @RequestMapping(value="/artistportal", method=RequestMethod.GET)
    public ModelAndView getArtistPortal() {
        Artist artist = artistManager.getSessionArtist();
        
        ModelAndView model = new ModelAndView();
        if (artist == null) {
            model.setViewName("artist_startup");
            model.addObject("artist", artist);
        } else {
            model.setViewName("artist_portal");
        }
        return model;
    }
    
    @RequestMapping(value="/adminportal", method=RequestMethod.GET)
    public ModelAndView getAdminPortal() {
        User user = userAccountManager.getSessionUser();
        
        ModelAndView model = new ModelAndView();
        if (user == null) {
            model.setViewName("startup");
            model.addObject("user", user);
        } else if (!user.isAdmin()) {
            model.setViewName("browse");
        } else {
            model.setViewName("admin_portal");
        }
        return model;
    }
}
