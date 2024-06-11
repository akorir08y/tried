package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionTracingMemberResponse {

    @JsonProperty("payload")
    private Tropayload tropayload;

    @JsonProperty("sessionNumber")
    private String sessionNumber;

    @JsonProperty("function")
    private String function;

    @JsonProperty("status")
    private Integer status;

    public TransactionTracingMemberResponse() {

    }

    public TransactionTracingMemberResponse(Tropayload tropayload, String sessionNumber, String function, Integer status) {
        this.tropayload = tropayload;
        this.sessionNumber = sessionNumber;
        this.function = function;
        this.status = status;
    }

    public Tropayload getTropayload() {
        return tropayload;
    }

    public void setTropayload(Tropayload tropayload) {
        this.tropayload = tropayload;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}