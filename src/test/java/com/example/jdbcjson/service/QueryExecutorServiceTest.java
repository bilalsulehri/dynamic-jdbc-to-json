package com.example.jdbcjson.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryExecutorServiceTest {

    @Autowired
    private QueryExecutorService queryExecutorService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS test_table");
        jdbcTemplate.execute("CREATE TABLE test_table (id INT, name VARCHAR(100), active BOOLEAN)");
        jdbcTemplate.execute("INSERT INTO test_table VALUES (1, 'Test 1', true)");
        jdbcTemplate.execute("INSERT INTO test_table VALUES (2, 'Test 2', false)");
    }

    @Test
    void testExecuteQuery() {
        JsonNode result = queryExecutorService.executeQuery("SELECT * FROM test_table");
        assertNotNull(result);
        assertTrue(result.isArray());
        assertEquals(2, result.size());
    }

    @Test
    void testExecuteQueryWithParameters() {
        JsonNode result = queryExecutorService.executeQuery(
                "SELECT * FROM test_table WHERE id = :id",
                Collections.singletonMap("id", 1)
        );
        assertNotNull(result);
        assertTrue(result.isArray());
        assertEquals(1, result.size());
    }

    @Test
    void testGetQueryMetadata() {
        List<String> metadata = queryExecutorService.getQueryMetadata("SELECT * FROM test_table");
        assertNotNull(metadata);
        assertFalse(metadata.isEmpty());
        assertTrue(metadata.get(0).contains("ID (INTEGER)"));
        assertTrue(metadata.get(1).contains("NAME (VARCHAR)"));
        assertTrue(metadata.get(2).contains("ACTIVE (BOOLEAN)"));
    }
}