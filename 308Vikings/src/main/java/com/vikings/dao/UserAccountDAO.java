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
     * Registers the given User.
     */
    public void registerUser(User user) {
        userAccountMapper.registerUser(user);
    }
    
    /**
     * Checks if this User exists.
     * @return
     *  Full User object if found, null otherwise.
     */
    public User processLogin(User user) {
        return userAccountMapper.processLogin(user);
    }
    
}
