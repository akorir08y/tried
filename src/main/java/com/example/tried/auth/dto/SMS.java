package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class SMS {

    @JsonProperty("authentication")
    private Authentication authentication;

    @JsonProperty("smsRequest")
    private SmsRequest smsRequest;

    @JsonProperty("function")
    private String function;

    public SMS() {
    }

    public SMS(Authentication authentication, SmsRequest smsRequest, String function) {
        this.authentication = authentication;
        this.smsRequest = smsRequest;
        this.function = function;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public SmsRequest getSmsRequest() {
        return smsRequest;
    }

    public void setSmsRequest(SmsRequest smsRequest) {
        this.smsRequest = smsRequest;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
