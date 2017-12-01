package com.vikings.dao.mapper;

import com.vikings.domain.User;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper class for UserAccountDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/UserAccountMapper.xml
 */
public interface UserAccountMapper {
    
    public boolean userExists(User user);
    
    public void registerUser(User user);
    
    public User processLogin(User user);
    
    public User getUserByUsername(String username);
    
    public boolean isValidUpdate(User user);
    
    public boolean passwordMatches(@Param("userId") String userId, @Param("hashedPassword") String hashedPassword);
    
    public void updateUser(User user);
    
    public void deleteUser(String id);
    
    public Set<User> getPremiumUsersForMonthlyRevenue();
    
}

