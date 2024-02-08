package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OTP {

    @JsonProperty("otp")
    private int OTP;
}
