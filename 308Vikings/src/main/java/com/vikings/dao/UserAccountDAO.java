package com.vikings.dao;

import com.vikings.dao.mapper.PaymentMapper;
import com.vikings.dao.mapper.UserAccountMapper;
import com.vikings.dao.mapper.UserMusicMapper;
import com.vikings.domain.User;
import com.vikings.domain.UserMusic;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDAO {
    
    @Autowired
    UserAccountMapper userAccountMapper;
    
    @Autowired
    UserMusicMapper userMusicMapper;
    
    @Autowired
    PaymentMapper paymentMapper;
    
    /**
     * Checks if the given user exists.
     * @param user
     *  User with valid username and email.
     * @return 
     *  true if exists, false if no one with that username or email.
     */
    public boolean userExists(User user) {
        return userAccountMapper.userExists(user);
    }
    
    /**
     * Registers the given User.
     * @param user
     *  The validated User to register.
     */
    public void registerUser(User user) {
        userAccountMapper.registerUser(user);
    }
    
    /**
     * Checks if this User exists and returns the User.
     * @param user
     *  User containing the desired username and password.
     * @return
     *  Full User object if found, null otherwise.
     */
    public User processLogin(User user) {
        User foundUser = userAccountMapper.processLogin(user);
        if (foundUser != null) {
            UserMusic userMusic = new UserMusic();
            userMusic.setSavedSongs(userMusicMapper.getSavedSongs(foundUser.getId()));
            userMusic.setSavedAlbums(userMusicMapper.getSavedAlbums(foundUser.getId()));
            userMusic.setFollowedArtists(userMusicMapper.getFollowedArtists(foundUser.getId()));
            userMusic.setFollowedPlaylists(userMusicMapper.getFollowedPlaylists(foundUser.getId()));
            userMusic.setHistory(userMusicMapper.getHistory(foundUser.getId()));
            userMusic.setRecentlyPlayed(userMusicMapper.getRecentlyPlayed(foundUser.getId()));
            foundUser.setUserMusic(userMusic);
            foundUser.setPayment(paymentMapper.getPaymentForUser(foundUser.getId()));
        }
        return foundUser;
    }
    
    public User getUserByUsername(String username) {
        return userAccountMapper.getUserByUsername(username);
    }
    
    public boolean isValidUpdate(User user) {
        return userAccountMapper.isValidUpdate(user);
    }
    
    /**
     * Updates the basic information of the User with the given ID.
     * !!! Make sure the password is hashed before updating !!!
     * @param user 
     *  User with the desired ID and desired new attributes.
     */
    public void updateUser(User user) {
        userAccountMapper.updateUser(user);
    }
    
    public void deleteUser(String id) {
        userAccountMapper.deleteUser(id);
    }
    
    public Set<User> getPremiumUsersForMonthlyRevenue() {
        return userAccountMapper.getPremiumUsersForMonthlyRevenue();
    }
    
}
