package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionsItem{

	@JsonProperty("transactionDetails")
	private TransactionDetails transactionDetails;

	@JsonProperty("cfms_transaction_id")
	private String cfmsTransactionId;

	@JsonProperty("receiver_id")
	private String receiverId;

	@JsonProperty("special_trust_funds")
	private String specialTrustFunds;

	@JsonProperty("giving_status")
	private String givingStatus;

	@JsonProperty("non_trust_fund")
	private String nonTrustFund;

	@JsonProperty("trust_fund")
	private String trustFund;

	@JsonProperty("settlement_status")
	private String settlementStatus;

	public TransactionsItem() {

	}

	public TransactionsItem(TransactionDetails transactionDetails, String cfmsTransactionId, String receiverId, String specialTrustFunds, String givingStatus, String nonTrustFund, String trustFund, String settlementStatus) {
		this.transactionDetails = transactionDetails;
		this.cfmsTransactionId = cfmsTransactionId;
		this.receiverId = receiverId;
		this.specialTrustFunds = specialTrustFunds;
		this.givingStatus = givingStatus;
		this.nonTrustFund = nonTrustFund;
		this.trustFund = trustFund;
		this.settlementStatus = settlementStatus;
	}

	public TransactionDetails getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(TransactionDetails transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public String getCfmsTransactionId() {
		return cfmsTransactionId;
	}

	public void setCfmsTransactionId(String cfmsTransactionId) {
		this.cfmsTransactionId = cfmsTransactionId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(String specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}

	public String getGivingStatus() {
		return givingStatus;
	}

	public void setGivingStatus(String givingStatus) {
		this.givingStatus = givingStatus;
	}

	public String getNonTrustFund() {
		return nonTrustFund;
	}

	public void setNonTrustFund(String nonTrustFund) {
		this.nonTrustFund = nonTrustFund;
	}

	public String getTrustFund() {
		return trustFund;
	}

	public void setTrustFund(String trustFund) {
		this.trustFund = trustFund;
	}

	public String getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
	}
}