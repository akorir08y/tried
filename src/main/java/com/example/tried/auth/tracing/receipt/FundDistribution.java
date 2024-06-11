package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

public class FundDistribution{

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;

	public FundDistribution() {

	}

	public FundDistribution(HashMap<String, Object> trustFunds, HashMap<String, Object> nonTrustFunds, HashMap<String, Object> specialTrustFunds) {
		this.trustFunds = trustFunds;
		this.nonTrustFunds = nonTrustFunds;
		this.specialTrustFunds = specialTrustFunds;
	}

	public HashMap<String, Object> getTrustFunds() {
		return trustFunds;
	}

	public void setTrustFunds(HashMap<String, Object> trustFunds) {
		this.trustFunds = trustFunds;
	}

	public HashMap<String, Object> getNonTrustFunds() {
		return nonTrustFunds;
	}

	public void setNonTrustFunds(HashMap<String, Object> nonTrustFunds) {
		this.nonTrustFunds = nonTrustFunds;
	}

	public HashMap<String, Object> getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(HashMap<String, Object> specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}
}