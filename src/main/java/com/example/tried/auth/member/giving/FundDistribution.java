package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Data
public class FundDistribution{

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Integer> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Integer> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;

}