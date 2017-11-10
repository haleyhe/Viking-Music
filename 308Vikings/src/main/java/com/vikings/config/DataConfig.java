package com.vikings.config;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * Spring config for MyBatis DAO layer.
 * Thanks to Michael Lanyon for helping me figure out this config:
 * https://blog.lanyonm.org/articles/2014/04/21/spring-4-mybatis-java-config.html
 * 
 */
@Configuration
@MapperScan("com.vikings.dao.mapper")
public class DataConfig {
    
    public static final Class DATABASE_DRIVERCLASS = com.mysql.jdbc.Driver.class;
    public static final String DATABASE_URL = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/vikings";
    public static final String DATABASE_USERNAME = "vikings";
    public static final String DATABASE_PASSWORD = "password";
    
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(DATABASE_DRIVERCLASS);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setPassword(DATABASE_PASSWORD);
	
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        // for identifying domain objects to map to
        sqlSessionFactory.setTypeAliasesPackage("com.vikings.domain");
        return sqlSessionFactory;
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
}