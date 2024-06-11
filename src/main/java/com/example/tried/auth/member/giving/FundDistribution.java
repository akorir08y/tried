package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.*;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FundDistribution{

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Integer> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Integer> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;

	public FundDistribution() {
	}

	public FundDistribution(HashMap<String, Integer> specialTrustFunds, HashMap<String, Integer> nonTrustFunds, HashMap<String, Integer> trustFunds) {
		this.specialTrustFunds = specialTrustFunds;
		this.nonTrustFunds = nonTrustFunds;
		this.trustFunds = trustFunds;
	}

	public HashMap<String, Integer> getTrustFunds() {
		return trustFunds;
	}

	public void setTrustFunds(HashMap<String, Integer> trustFunds) {
		this.trustFunds = trustFunds;
	}

	public HashMap<String, Integer> getNonTrustFunds() {
		return nonTrustFunds;
	}

	public void setNonTrustFunds(HashMap<String, Integer> nonTrustFunds) {
		this.nonTrustFunds = nonTrustFunds;
	}

	public HashMap<String, Integer> getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(HashMap<String, Integer> specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}
}