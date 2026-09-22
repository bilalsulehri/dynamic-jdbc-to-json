package com.example.jdbcjson.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        // Goes through DataSourceProperties rather than binding @ConfigurationProperties
        // straight onto the pooled DataSource: Hikari's setter is setJdbcUrl(), not setUrl(),
        // so a raw DataSourceBuilder().build() bean never actually binds spring.datasource.url.
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = {"JdbcTemplate"})
    JdbcTemplate createDwhVerticaTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = {"JdbcNamedTemplate"})
    NamedParameterJdbcTemplate createJdbcNamedTemplated(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}

