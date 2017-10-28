package com.vikings.dao.mapper;

import com.vikings.domain.User;

/**
 * Mapper class for UserAccountDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/UserAccountMapper.xml
 */
public interface UserAccountMapper {
    
    public boolean userExists(User user);
    
    public void registerUser(User user);
    
    public User processLogin(User user);
    
    public void updateUser(User user);
    
}

