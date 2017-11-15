package com.vikings.controller;

import com.vikings.domain.Name;
import com.vikings.domain.User;
import com.vikings.domain.response.JsonResponse;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.FileManager;
import com.vikings.manager.UserAccountManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
    
    @Autowired
    ArtistManager artistManager;
    
    @Autowired
    UserAccountManager userAccountManager;
    
    @Autowired
    FileManager fileManager;
    
    @RequestMapping(method=RequestMethod.GET, value="/Admin/getUserByUsername")
    public @ResponseBody User getUserByUsername(@RequestParam("username") String username) {
        User sessionUser = userAccountManager.getSessionUser();
        if (sessionUser == null | !sessionUser.isAdmin()) {
            return null;
        }
        
        return userAccountManager.getUserByUsername(username);
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
    @RequestMapping(method=RequestMethod.POST, value="/Admin/updateUser")
    public @ResponseBody JsonResponse updateUserForAdmin(@RequestBody User updatedUser) {
        User sessionUser = userAccountManager.getSessionUser();
        if (sessionUser == null | !sessionUser.isAdmin()) {
            return new JsonResponse(false, System.getProperty("error.UserAccount.sessionExpired"));
        }
        
        if (userAccountManager.updateUserProfile(updatedUser)) {
            return new JsonResponse(true);
        } else {
            return new JsonResponse (false, System.getProperty("error.UserAccount.profileUpdateFail"));
        }  
    }
    
    /**
     * Updates the Artist with the given ID with new given bio and name, and adds
     * genre or name if provided.
     * @param id
     *  Artist ID.
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
    @RequestMapping(method=RequestMethod.POST, value="/Admin/updateArtist")
    public @ResponseBody JsonResponse updateArtistForAdmin(
            @RequestParam(value="thumbnail", required=false) MultipartFile thumbnail,
            @RequestParam(value="id") String id,
            @RequestParam(value="name") String name,
            @RequestParam(value="bio") String bio,
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName,
            @RequestParam(value="genre") String genre) {
        
        User sessionUser = userAccountManager.getSessionUser();
        
        // check for nulls and account for empty/invalid parameters
        if (sessionUser == null | !sessionUser.isAdmin()) {
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
        
        if (thumbnail != null) {
            boolean result = fileManager.uploadArtistThumbnail(thumbnail, id);
            if (result == false) {
                return new JsonResponse(false, System.getProperty("error.Artist.thumbnailUploadFail"));
            }
        }
        
        artistManager.updateArtist(id, name, bio, newName, genre);
        return new JsonResponse(true); 
    }
}
