package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.web.bind.annotation.RequestParam;


public class OfferStatement {

    @JsonProperty("phone_number")
    String phone_number;
    @JsonProperty("start_date")
    String start_date;
    @JsonProperty("end_date")
    String end_date;
    @JsonProperty("pin")
    String pin;

    public OfferStatement() {

    }

    public OfferStatement(String phone_number, String start_date, String end_date, String pin) {
        this.phone_number = phone_number;
        this.start_date = start_date;
        this.end_date = end_date;
        this.pin = pin;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
