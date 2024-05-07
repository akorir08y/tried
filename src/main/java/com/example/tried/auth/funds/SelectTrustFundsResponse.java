package com.example.tried.auth.funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectTrustFundsResponse{

    @JsonProperty("function")
    private String function;

    @JsonProperty("payload")
    private Slcpayload slcpayload;

    @JsonProperty("status")
    private Integer status;
}