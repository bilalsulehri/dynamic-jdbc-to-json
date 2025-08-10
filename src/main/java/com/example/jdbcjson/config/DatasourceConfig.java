package com.example.jdbcjson.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    
    @Bean(name = {"JdbcTemplate"})
    JdbcTemplate createDwhVerticaTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(name = {"JdbcNamedTemplate"})
    NamedParameterJdbcTemplate createJdbcNamedTemplated() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

}

