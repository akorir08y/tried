package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class TransactionDetails{

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;
}