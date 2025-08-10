package com.example.jdbcjson.service;

import com.example.jdbcjson.util.ResultSetToJsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.util.Collections;
import java.util.List;
import java.util.Map;


public class QueryExecutorServiceImpl implements QueryExecutorService {

   

    @Override
    public JsonNode executeQuery(String sql) {
       return null;
    }

    @Override
    public JsonNode executeQuery(String sql, Map<String, Object> parameters) {
        return null;
    }

    @Override
    public List<String> getQueryMetadata(String sql) {
        return null;
    }

}
