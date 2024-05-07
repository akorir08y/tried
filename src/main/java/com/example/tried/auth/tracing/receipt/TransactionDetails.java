package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class TransactionDetails{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;
}