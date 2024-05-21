package com.example.tried.auth.personnel.receipting.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class FundDistribution{

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Integer> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Integer> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;
}