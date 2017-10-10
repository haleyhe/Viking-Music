package com.vikings.dao.mapper;

import com.vikings.domain.TestDomainObj;

/**
 * Mapper class for TestDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/TestMapper.xml
 */
public interface TestMapper {
    
    public boolean dbAliveTest();
    
    public TestDomainObj getObj(String id);
    
}

