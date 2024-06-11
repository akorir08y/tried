package com.example.tried.auth.personnel.reports.offering;

import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("localChurchFunds")
	private HashMap<String, Integer> localChurchFunds;

	@JsonProperty("members")
	private List<HashMap<String, Object>> members;

	@JsonProperty("meansOfPayment")
	private List<String> meansOfPayment;

	@JsonProperty("trustFunds")
	private HashMap<String,String> trustFunds;

	public Payload() {
	}

	public Payload(HashMap<String, Integer> localChurchFunds, List<HashMap<String, Object>> members, List<String> meansOfPayment, HashMap<String, String> trustFunds) {
		this.localChurchFunds = localChurchFunds;
		this.members = members;
		this.meansOfPayment = meansOfPayment;
		this.trustFunds = trustFunds;
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

	public HashMap<String, String> getTrustFunds() {
		return trustFunds;
	}

	public void setTrustFunds(HashMap<String, String> trustFunds) {
		this.trustFunds = trustFunds;
	}
}