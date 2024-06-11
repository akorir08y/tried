package com.example.tried.dto.c2b;


import com.fasterxml.jackson.annotation.JsonProperty;


// Register URL Request

public class RegisterUrlResponse {

    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("ResponseDescription")
    private String responseDescription;

    @JsonProperty("OriginatorCoversationID")
    private String originatorCoversationID;

    public RegisterUrlResponse() {

    }

    public RegisterUrlResponse(String originatorCoversationID, String responseDescription, String responseCode) {
        this.originatorCoversationID = originatorCoversationID;
        this.responseDescription = responseDescription;
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getOriginatorCoversationID() {
        return originatorCoversationID;
    }

    public void setOriginatorCoversationID(String originatorCoversationID) {
        this.originatorCoversationID = originatorCoversationID;
    }
}
