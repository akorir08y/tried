package com.example.tried.auth.dto;

import lombok.Data;

@Data
public class OtpValidationRequest {
    private String username;
    private String otpNumber;
}
