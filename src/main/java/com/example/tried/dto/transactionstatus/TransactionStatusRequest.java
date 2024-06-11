package com.example.tried.dto.transactionstatus;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionStatusRequest{

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Occasion")
	private String occasion;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("PartyA")
	private int partyA;

	@JsonProperty("IdentifierType")
	private int identifierType;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("ResultURL")
	private String resultURL;

	public TransactionStatusRequest() {

	}

	public TransactionStatusRequest(String queueTimeOutURL, String initiator, String originatorConversationID, String remarks, String occasion, String securityCredential, int partyA, int identifierType, String commandID, String transactionID, String resultURL) {
		this.queueTimeOutURL = queueTimeOutURL;
		this.initiator = initiator;
		this.originatorConversationID = originatorConversationID;
		this.remarks = remarks;
		this.occasion = occasion;
		this.securityCredential = securityCredential;
		this.partyA = partyA;
		this.identifierType = identifierType;
		this.commandID = commandID;
		this.transactionID = transactionID;
		this.resultURL = resultURL;
	}

	public String getQueueTimeOutURL() {
		return queueTimeOutURL;
	}

	public void setQueueTimeOutURL(String queueTimeOutURL) {
		this.queueTimeOutURL = queueTimeOutURL;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getOriginatorConversationID() {
		return originatorConversationID;
	}

	public void setOriginatorConversationID(String originatorConversationID) {
		this.originatorConversationID = originatorConversationID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getSecurityCredential() {
		return securityCredential;
	}

	public void setSecurityCredential(String securityCredential) {
		this.securityCredential = securityCredential;
	}

	public int getPartyA() {
		return partyA;
	}

	public void setPartyA(int partyA) {
		this.partyA = partyA;
	}

	public int getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(int identifierType) {
		this.identifierType = identifierType;
	}

	public String getCommandID() {
		return commandID;
	}

	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
}