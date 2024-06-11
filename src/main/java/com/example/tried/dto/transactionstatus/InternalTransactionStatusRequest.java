package com.example.tried.dto.transactionstatus;

import com.fasterxml.jackson.annotation.JsonProperty;



public class InternalTransactionStatusRequest{

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;

	public InternalTransactionStatusRequest() {

	}

	public InternalTransactionStatusRequest(String transactionID, String originatorConversationID) {
		this.transactionID = transactionID;
		this.originatorConversationID = originatorConversationID;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getOriginatorConversationID() {
		return originatorConversationID;
	}

	public void setOriginatorConversationID(String originatorConversationID) {
		this.originatorConversationID = originatorConversationID;
	}
}