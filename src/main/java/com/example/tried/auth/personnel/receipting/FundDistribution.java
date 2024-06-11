package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

public class FundDistribution{

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Integer> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Integer> specialTrustFunds;

	public FundDistribution() {
	}

	public FundDistribution(HashMap<String, Integer> nonTrustFunds, HashMap<String, Integer> trustFunds, HashMap<String, Integer> specialTrustFunds) {
		this.nonTrustFunds = nonTrustFunds;
		this.trustFunds = trustFunds;
		this.specialTrustFunds = specialTrustFunds;
	}

	public HashMap<String, Integer> getNonTrustFunds() {
		return nonTrustFunds;
	}

	public void setNonTrustFunds(HashMap<String, Integer> nonTrustFunds) {
		this.nonTrustFunds = nonTrustFunds;
	}

	public HashMap<String, Integer> getTrustFunds() {
		return trustFunds;
	}

	public void setTrustFunds(HashMap<String, Integer> trustFunds) {
		this.trustFunds = trustFunds;
	}

	public HashMap<String, Integer> getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(HashMap<String, Integer> specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}
}