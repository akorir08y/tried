package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

public class FundDistribution{

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;

	public FundDistribution() {

	}

	public FundDistribution(HashMap<String, Integer> trustFunds) {
		this.trustFunds = trustFunds;
	}

	public void setTrustFunds(HashMap<String, Integer> trustFunds) {
		this.trustFunds = trustFunds;
	}
}