package com.example.tried.dto.c2b;

import com.fasterxml.jackson.annotation.JsonProperty;


// Register URL Request
public class RegisterUrlRequest {

    @JsonProperty("ShortCode")
    private int shortCode;

    @JsonProperty("ConfirmationURL")
    private String confirmationURL;

    @JsonProperty("ValidationURL")
    private String validationURL;

    @JsonProperty("ResponseType")
    private String responseType;

    public RegisterUrlRequest() {

    }

    public RegisterUrlRequest(int shortCode, String confirmationURL, String validationURL, String responseType) {
        this.shortCode = shortCode;
        this.confirmationURL = confirmationURL;
        this.validationURL = validationURL;
        this.responseType = responseType;
    }

    public int getShortCode() {
        return shortCode;
    }

    public void setShortCode(int shortCode) {
        this.shortCode = shortCode;
    }

    public String getConfirmationURL() {
        return confirmationURL;
    }

    public void setConfirmationURL(String confirmationURL) {
        this.confirmationURL = confirmationURL;
    }

    public String getValidationURL() {
        return validationURL;
    }

    public void setValidationURL(String validationURL) {
        this.validationURL = validationURL;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
