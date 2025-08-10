package com.example.jdbcjson.service.dynamic;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DynamicService {

    private final QueryExecutorService queryExecutorService;
    public DynamicService(QueryExecutorService queryExecutorService) {
        this.queryExecutorService = queryExecutorService;
    }

    public GenericResponse getChatbotWidgetAggregates(String startDate, String endDate
                                               , String customerId,
                                               int limit, int offset) {

        String sql = "SELECT * FROM customers";

        String countQuery = "Select count(1) as total_count from customers " ;


        String whereClause = " WHERE  " +
                "created_at >= :startDate AND created_at <= :endDate " +
                " AND customer_id = :customerId ";


        Map<String, Object> params= Map.of(
                "startDate", startDate,
                "endDate", endDate,
                "customerId", customerId,
                "limit", limit,
                "offset", offset
        );
        log.info("Executing query");
        JsonNode result = queryExecutorService.executeQuery(sql + whereClause +"  offset :offset limit :limit  ", params);
        log.info("Query executed with json node");
        JsonNode countResult = queryExecutorService.executeQuery(countQuery + whereClause, Map.of(
                "startDate", startDate,
                "endDate", endDate,
                "customerId", customerId
        ));
        int totalCount = countResult.get(0).get("total_count").asInt();
        GenericResponse response = new GenericResponse(totalCount, result.size(), result);
        return response;
    }
}
