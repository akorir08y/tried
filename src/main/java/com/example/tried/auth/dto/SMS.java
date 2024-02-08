package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SMS {

    @JsonProperty("authentication")
    private Authentication authentication;
    @JsonProperty("smsRequest")
    private SmsRequest smsRequest;
    @JsonProperty("function")
    private String function;
}
