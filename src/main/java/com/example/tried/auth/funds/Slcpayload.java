package com.example.tried.auth.funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
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
}