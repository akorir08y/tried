package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundDistribution{

	@JsonProperty("specialTrustFunds")
	private SpecialTrustFunds specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private NonTrustFunds nonTrustFunds;

	@JsonProperty("trustFunds")
	private TrustFunds trustFunds;
}