package com.vikings.controller;

import com.vikings.domain.Artist;
import com.vikings.domain.Name;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

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
     * @param thumbnail
     *  New artist image (optional)
     * @param name
     *  Artist name
     * @param bio
     *  Artist bio
     * @param firstName
     *  First name of new Related Name
     * @param lastName
     *  Last name of new Related Name
     * @param genre
     *  New associated genre
     * @return 
     *  JsonResponse indicating success or fail.
     */
    @RequestMapping(method=RequestMethod.POST, value="/ArtistAccount/updateArtist")
    public @ResponseBody JsonResponse updateArtist(
            @RequestParam(value="thumbnail", required=false) MultipartFile thumbnail,
            @RequestParam(value="name") String name,
            @RequestParam(value="bio") String bio,
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName,
            @RequestParam(value="genre") String genre) {
        
        Artist sessionArtist = artistManager.getSessionArtist();
        
        if (sessionArtist == null) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }
        Name newName = new Name(firstName, lastName);
        if (name.trim().isEmpty()) {
            return new JsonResponse(false, System.getProperty("error.Form.invalidParameters"));
        }
        if (firstName.trim().isEmpty()
            || lastName.trim().isEmpty()) {
            newName = null;
        }
        if (genre.trim().isEmpty()) {
            genre = null;
        }
        
        Artist resultArtist = artistManager.updateArtist(sessionArtist.getId(), name, bio, newName, genre);
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