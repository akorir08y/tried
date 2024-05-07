package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;


@Data
public class FundDistribution{

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;
}