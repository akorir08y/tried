package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}
