package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class FundDistribution{

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Integer> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Integer> specialTrustFunds;
}