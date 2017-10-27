package com.vikings.dao;

import com.vikings.dao.mapper.UserAccountMapper;
import com.vikings.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO for User Account actions
 * (e.g. sign up, sign in, edit profile)
 */
@Repository
public class UserAccountDAO {
    
    @Autowired
    UserAccountMapper userAccountMapper;
    
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
        return userAccountMapper.processLogin(user);
    }
    
    //@TODO
    public void updateUser(User user) {
        //@TODO
    }
    
}
