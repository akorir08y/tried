package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.JSONObject;

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