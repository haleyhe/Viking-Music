package com.vikings.controller;

import com.vikings.domain.Artist;
import com.vikings.domain.requests.JsonResponse;
import com.vikings.domain.requests.LoginRequest;
import com.vikings.domain.requests.UpdateArtistRequest;
import com.vikings.manager.ArtistManager;
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
public class ArtistAccountController {
    
    @Autowired
    ArtistManager artistManager;
    
    /**
     * Attempts to log in with the given Artist ID and password, adding
     * to HttpSession if success.
     * @param loginRequest
     *  LoginRequest object containing the username (Artist ID) and password.
     * @return 
     *  true if Artist found and added to session, false if not found.
     */
    @RequestMapping(method=RequestMethod.POST, value="/ArtistAccount/processLogin")
    public @ResponseBody JsonResponse processLogin(@RequestBody LoginRequest loginRequest) {        
        Artist artist =  artistManager.getArtistAccount(loginRequest.getUsername(), loginRequest.getPassword());
        
        JsonResponse json = new JsonResponse();
        if (artist != null) {
            artistManager.setSessionArtist(artist);
            json.setSuccess(true);
        } else {
            json.setSuccess(false);
            json.setError(System.getProperty("error.UserAccount.invalidLogin"));
        }
        return json;
    }
    
    /**
     * Updates the Artist in the session with the new given bio and name, and adds
     * genre or name if provided.
     * @param updateArtistRequest
     *  Request object with new parameters (including new name or new genre).
     * @return 
     *  JsonResponse indicating success or fail.
     */
    @RequestMapping(method=RequestMethod.POST, value="/ArtistAccount/updateArtist")
    public @ResponseBody JsonResponse updateArtist(@RequestBody UpdateArtistRequest updateArtistRequest) {
        Artist sessionArtist = artistManager.getSessionArtist();
        
        if (sessionArtist == null) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }
        if (updateArtistRequest.getName().trim().isEmpty()) {
            return new JsonResponse(false, System.getProperty("error.Form.invalidParameters"));
        }
        if (updateArtistRequest.getRelatedName().getFirstName().trim().isEmpty()
            || updateArtistRequest.getRelatedName().getLastName().trim().isEmpty()) {
            updateArtistRequest.setRelatedName(null);
        }
        if (updateArtistRequest.getGenre().trim().isEmpty()) {
            updateArtistRequest.setGenre(null);
        }
        
        Artist resultArtist = artistManager.updateArtist(sessionArtist.getId(), updateArtistRequest);
        artistManager.setSessionArtist(resultArtist);
        
        return new JsonResponse(true); 
    }
       
    /**
     * Logs the Artist in the session out.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/ArtistAccount/logout")
    public @ResponseBody JsonResponse logout() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.invalidate();    
        return new JsonResponse(true);
    }
}
