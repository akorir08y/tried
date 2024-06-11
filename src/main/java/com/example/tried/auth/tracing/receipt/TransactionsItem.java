package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionsItem{

	@JsonProperty("transactionDetails")
	private TransactionDetails transactionDetails;

	@JsonProperty("cfms_transaction_id")
	private String cfmsTransactionId;

	@JsonProperty("receiver_id")
	private String receiverId;

	@JsonProperty("non_trust_fund")
	private String nonTrustFunds;

	@JsonProperty("special_trust_funds")
	private String specialTrustFunds;

	@JsonProperty("trust_fund")
	private String trustFund;

	@JsonProperty("giving_status")
	private String givingStatus;

	@JsonProperty("settlement_status")
	private String settlementStatus;

	public TransactionsItem() {

	}

	public TransactionsItem(TransactionDetails transactionDetails, String cfmsTransactionId, String receiverId, String nonTrustFunds, String specialTrustFunds, String trustFund, String givingStatus, String settlementStatus) {
		this.transactionDetails = transactionDetails;
		this.cfmsTransactionId = cfmsTransactionId;
		this.receiverId = receiverId;
		this.nonTrustFunds = nonTrustFunds;
		this.specialTrustFunds = specialTrustFunds;
		this.trustFund = trustFund;
		this.givingStatus = givingStatus;
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

	public String getNonTrustFunds() {
		return nonTrustFunds;
	}

	public void setNonTrustFunds(String nonTrustFunds) {
		this.nonTrustFunds = nonTrustFunds;
	}

	public String getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(String specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}

	public String getTrustFund() {
		return trustFund;
	}

	public void setTrustFund(String trustFund) {
		this.trustFund = trustFund;
	}

	public String getGivingStatus() {
		return givingStatus;
	}

	public void setGivingStatus(String givingStatus) {
		this.givingStatus = givingStatus;
	}

	public String getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
	}
}