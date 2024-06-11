package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

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

	public TransactionDetails() {

	}

	public TransactionDetails(Member member, FundDistribution fundDistribution, HashMap<String, Object> trustFunds, HashMap<String, Object> nonTrustFunds, HashMap<String, Object> specialTrustFunds) {
		this.member = member;
		this.fundDistribution = fundDistribution;
		this.trustFunds = trustFunds;
		this.nonTrustFunds = nonTrustFunds;
		this.specialTrustFunds = specialTrustFunds;
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