package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class FundDistribution{

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;
}