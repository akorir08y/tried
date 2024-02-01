package com.example.tried.dto.c2b;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Register URL Request
@Data
public class RegisterUrlResponse {

    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("ResponseDescription")
    private String responseDescription;

    @JsonProperty("OriginatorCoversationID")
    private String originatorCoversationID;

}
