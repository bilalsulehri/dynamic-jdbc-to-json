package com.example.jdbcjson.service.dynamic;

import com.fasterxml.jackson.databind.JsonNode;

public class GenericResponse {

    int totalCount;
    int itemCount;
    JsonNode data;

    //constructor
    public GenericResponse(int totalCount, int itemCount, JsonNode data) {
        this.totalCount = totalCount;
        this.itemCount = itemCount;
        this.data = data;
    }
    //getters
    public int getTotalCount() {
        return totalCount;
    }
    public int getItemCount() {
        return itemCount;
    }
    public JsonNode getData() {
        return data;
    }
    //setters
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public void setData(JsonNode data) {
        this.data = data;
    }

}
