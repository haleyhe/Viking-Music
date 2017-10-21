package com.vikings.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.vikings.dao.mapper.TestMapper;

/**
 * Test DAO for database access.
 */
@Repository
public class TestDAO {
    
    @Autowired
    TestMapper testMapper;
    
    /**
     * Tests to see if a DB connection has been established.
     * 
     * @return True if query succeeds, exception otherwise.
     */
    public boolean dbAliveTest() {
        return testMapper.dbAliveTest();
    }
    
}
