package com.example.tried.auth.personnel.receipting.traced.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class FundDistribution{

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;
}