package com.example.tried.auth.funds;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Slcpayload{

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("sessionNumber")
    private Integer sessionNumber;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("fundDistribution")
    private HashMap<String, Integer> fundDistribution;

    @JsonProperty("startDate")
    private String startDate;

    public Slcpayload() {
    }

    public Slcpayload(Integer amount, Integer sessionNumber, String endDate, HashMap<String, Integer> fundDistribution, String startDate) {
        this.amount = amount;
        this.sessionNumber = sessionNumber;
        this.endDate = endDate;
        this.fundDistribution = fundDistribution;
        this.startDate = startDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public HashMap<String, Integer> getFundDistribution() {
        return fundDistribution;
    }

    public void setFundDistribution(HashMap<String, Integer> fundDistribution) {
        this.fundDistribution = fundDistribution;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}