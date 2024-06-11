package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

public class FundDistribution {

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	public FundDistribution() {

	}

	public FundDistribution(HashMap<String, Object> specialTrustFunds, HashMap<String, Object> nonTrustFunds, HashMap<String, Object> trustFunds) {
		this.specialTrustFunds = specialTrustFunds;
		this.nonTrustFunds = nonTrustFunds;
		this.trustFunds = trustFunds;
	}

	public HashMap<String, Object> getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(HashMap<String, Object> specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}

	public HashMap<String, Object> getNonTrustFunds() {
		return nonTrustFunds;
	}

	public void setNonTrustFunds(HashMap<String, Object> nonTrustFunds) {
		this.nonTrustFunds = nonTrustFunds;
	}

	public HashMap<String, Object> getTrustFunds() {
		return trustFunds;
	}

	public void setTrustFunds(HashMap<String, Object> trustFunds) {
		this.trustFunds = trustFunds;
	}
}