package com.vikings.manager;

import com.vikings.dao.UserAccountDAO;
import com.vikings.domain.User;
import com.vikings.util.InputChecker;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager for User Account actions
 * (e.g. sign up, sign in, edit profile)
 */
@Service
public class UserAccountManager {
    
    @Autowired
    UserAccountDAO userAccountDAO;
    
    /**
     * Checks if the given user exists.
     * @param user
     *  User with valid username and email.
     * @return 
     *  true if exists, false if no one with that username or email.
     */
    public boolean userExists(User user) {
        return userAccountDAO.userExists(user);
    }
    
    /**
     * Registers the given User (assuming new user
     * info has been validated).
     * @param user 
     *  User object with the desired new user information
     *  (including unhashed password).
     */
    public void registerUser(User user) {
        // generate an ID
        user.setId(java.util.UUID.randomUUID().toString());
        // hash the password
        user.setPassword(hashPassword(user.getPassword()));
        
        // add new User to the database
        userAccountDAO.registerUser(user);
    }
    
    /**
     * Checks DB for user with the given credentials.
     * Returns full User object if found.
     * @param user
     *  User object with desired username and password.
     * @return
     *  The full User object if found, or null if none found.
     */
    public User processLogin(User user) {
        // hash the password.
        user.setPassword(hashPassword(user.getPassword()));
        
        return userAccountDAO.processLogin(user);
    }
    
    /**
     * Hashes password with SHA-512.
     * 
     * @param password
     *  Unhashed password
     * @return 
     *  Hashed password as a String
     */
    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] passwordBytes = password.getBytes();
            byte[] hashedBytes = messageDigest.digest(passwordBytes);
            return new String(hashedBytes);
        } catch (NoSuchAlgorithmException ex) {
            // don't hash the password
            return password;
        }
    }
    
    /**
     * Updates a user's profile based on their input
     * @param updatedUser object containing all of the fields that will be updated, everything not null will be updated
     * @return true if the new values are valid and the update successfully goes through. False otherwise
     */
    public boolean updateUserProfile(User updatedUser) {
        if (!hasValidUserParameters(updatedUser)) {
            return false;
        } else {
            userAccountDAO.updateUser(updatedUser);
            return true;
        }
    }
    
    /**
     * Helper method to validate the user info that the user entered
     * @param user object containing all of the fields that will be updated, everything not null will be updated
     * @return True if all of the checks are passed. False otherwise
     */
    private boolean hasValidUserParameters (User user) {    
        if (user.getEmail()!= null && !InputChecker.isValidEmail(user.getEmail())) {
            return false;
        }
        if (user.getZip()!= null && !InputChecker.isValidZip(user.getZip())) {
            return false;
        }
        return true;
    }
    
    
}
