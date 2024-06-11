package com.example.tried.auth.funds;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SelectTrustFundsResponse{

    @JsonProperty("function")
    private String function;

    @JsonProperty("payload")
    private Slcpayload slcpayload;

    @JsonProperty("status")
    private Integer status;

    public SelectTrustFundsResponse() {
    }

    public SelectTrustFundsResponse(String function, Slcpayload slcpayload, Integer status) {
        this.function = function;
        this.slcpayload = slcpayload;
        this.status = status;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Slcpayload getSlcpayload() {
        return slcpayload;
    }

    public void setSlcpayload(Slcpayload slcpayload) {
        this.slcpayload = slcpayload;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}