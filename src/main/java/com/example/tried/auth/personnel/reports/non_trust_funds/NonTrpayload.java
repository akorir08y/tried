package com.example.tried.auth.personnel.reports.non_trust_funds;

import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;



public class NonTrpayload{

	@JsonProperty("localChurchFunds")
	private HashMap<String, Integer> localChurchFunds;

	@JsonProperty("members")
	private List<HashMap<String, Object>> members;

	@JsonProperty("meansOfPayment")
	private List<String> meansOfPayment;

	public NonTrpayload() {
	}

	public NonTrpayload(HashMap<String, Integer> localChurchFunds, List<HashMap<String, Object>> members, List<String> meansOfPayment) {
		this.localChurchFunds = localChurchFunds;
		this.members = members;
		this.meansOfPayment = meansOfPayment;
	}

	public HashMap<String, Integer> getLocalChurchFunds() {
		return localChurchFunds;
	}

	public void setLocalChurchFunds(HashMap<String, Integer> localChurchFunds) {
		this.localChurchFunds = localChurchFunds;
	}

	public List<HashMap<String, Object>> getMembers() {
		return members;
	}

	public void setMembers(List<HashMap<String, Object>> members) {
		this.members = members;
	}

	public List<String> getMeansOfPayment() {
		return meansOfPayment;
	}

	public void setMeansOfPayment(List<String> meansOfPayment) {
		this.meansOfPayment = meansOfPayment;
	}
}