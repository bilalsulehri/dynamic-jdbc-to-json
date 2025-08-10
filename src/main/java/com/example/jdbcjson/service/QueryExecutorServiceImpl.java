package com.example.jdbcjson.service;

import com.example.jdbcjson.util.ResultSetToJsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QueryExecutorServiceImpl implements QueryExecutorService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public QueryExecutorServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public JsonNode executeQuery(String sql) {
        return executeQuery(sql, Collections.emptyMap());
    }

    @Override
    public JsonNode executeQuery(String sql, Map<String, Object> parameters) {
        return namedParameterJdbcTemplate.query(sql, parameters, ResultSetToJsonMapper::mapResultSetToJson);
    }

    @Override
    public List<String> getQueryMetadata(String sql) {
        return jdbcTemplate.query(sql, ResultSetToJsonMapper::extractColumnMetadata);
    }
}