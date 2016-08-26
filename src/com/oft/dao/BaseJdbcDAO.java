package com.oft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class BaseJdbcDAO {
	
    @Autowired
    @Qualifier("jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;


    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;


}
