package com.example.tried.dto.c2b;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Register URL Request
@Data
public class RegisterUrlRequest {

    @JsonProperty("ShortCode")
    private int shortCode;

    @JsonProperty("ConfirmationURL")
    private String confirmationURL;

    @JsonProperty("ValidationURL")
    private String validationURL;

    @JsonProperty("ResponseType")
    private String responseType;


}
