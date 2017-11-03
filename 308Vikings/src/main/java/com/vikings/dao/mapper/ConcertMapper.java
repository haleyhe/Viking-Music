package com.vikings.dao.mapper;

import com.vikings.domain.Concert;

/**
 * Mapper class for ConcertDAO.
 * The SQL queries are defined in 
 * resources/com/vikings/dao/mapper/ConcertMapper.xml
 */
public interface ConcertMapper {
    
    public Concert getConcert(String id);
    
}

