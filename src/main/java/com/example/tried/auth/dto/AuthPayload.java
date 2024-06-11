package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthPayload {

    @JsonProperty("sessionNumber")
    private String sessionNumber;

    @JsonProperty("pin")
    private String pin;

    @JsonProperty("accessNumber")
    private String accessNumber;

    @JsonProperty("numberOfTries")
    private String numberOfTries;

    @JsonProperty("version")
    private String version;

    public AuthPayload() {
    }

    public AuthPayload(String sessionNumber, String pin, String accessNumber, String numberOfTries, String version) {
        this.sessionNumber = sessionNumber;
        this.pin = pin;
        this.accessNumber = accessNumber;
        this.numberOfTries = numberOfTries;
        this.version = version;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public String getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(String numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
