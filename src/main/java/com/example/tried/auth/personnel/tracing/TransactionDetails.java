package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

public class TransactionDetails{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("accountDetails")
	private AccountDetails accountDetails;

	@JsonProperty("transferDuration")
	private TransferDuration transferDuration;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Object> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;

	public TransactionDetails() {

	}

	public TransactionDetails(Member member, FundDistribution fundDistribution, AccountDetails accountDetails, TransferDuration transferDuration, HashMap<String, Object> specialTrustFunds, HashMap<String, Object> nonTrustFunds, HashMap<String, Object> trustFunds) {
		this.member = member;
		this.fundDistribution = fundDistribution;
		this.accountDetails = accountDetails;
		this.transferDuration = transferDuration;
		this.specialTrustFunds = specialTrustFunds;
		this.nonTrustFunds = nonTrustFunds;
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

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public TransferDuration getTransferDuration() {
		return transferDuration;
	}

	public void setTransferDuration(TransferDuration transferDuration) {
		this.transferDuration = transferDuration;
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