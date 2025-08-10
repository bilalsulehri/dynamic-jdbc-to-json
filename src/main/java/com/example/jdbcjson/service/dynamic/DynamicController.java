package com.example.jdbcjson.service.dynamic;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dynamic")
public class DynamicController {

    private final DynamicService dynamicService;

    public DynamicController(DynamicService dynamicService) {
        this.dynamicService = dynamicService;
    }

    // Add your endpoint methods here
    // For example:
    @GetMapping("/get-customers")
    public GenericResponse getChatbotWidgetAggregates(
            String startDate,
            String endDate,
            String customerId,
            int limit,
            int offset) {
         return dynamicService.getChatbotWidgetAggregates(startDate, endDate, customerId, limit, offset);
     }
}
