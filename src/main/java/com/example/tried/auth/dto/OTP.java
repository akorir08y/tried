package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class OTP {

    @JsonProperty("otp")
    private int OTP;

    public OTP() {
    }

    public OTP(int OTP) {
        this.OTP = OTP;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }
}
