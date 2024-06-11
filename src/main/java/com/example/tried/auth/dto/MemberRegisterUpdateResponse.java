package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberRegisterUpdateResponse {

    @JsonProperty("sessionNumber")
    private int sessionNumber;

    @JsonProperty("function")
    private String function;

    @JsonProperty("status")
    private int status;

    public MemberRegisterUpdateResponse() {
    }

    public MemberRegisterUpdateResponse(int sessionNumber, String function, int status) {
        this.sessionNumber = sessionNumber;
        this.function = function;
        this.status = status;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
