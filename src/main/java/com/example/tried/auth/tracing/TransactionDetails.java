package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

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

	public TransactionDetails() {

	}

	public TransactionDetails(HashMap<String, Object> specialTrustFunds, HashMap<String, Object> nonTrustFunds, HashMap<String, Object> trustFunds, Member member, FundDistribution fundDistribution) {
		this.specialTrustFunds = specialTrustFunds;
		this.nonTrustFunds = nonTrustFunds;
		this.trustFunds = trustFunds;
		this.member = member;
		this.fundDistribution = fundDistribution;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public FundDistribution getFundDistribution() {
		return fundDistribution;
	}

	public void setFundDistribution(FundDistribution fundDistribution) {
		this.fundDistribution = fundDistribution;
	}
}