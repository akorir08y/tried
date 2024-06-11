package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionReversal{

	@JsonProperty("RecieverIdentifierType")
	private String recieverIdentifierType;

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("Occassion")
	private String occassion;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("ReceiverParty")
	private String receiverParty;

	@JsonProperty("ResultURL")
	private String resultURL;

	public TransactionReversal() {

	}

	public TransactionReversal(String recieverIdentifierType, String queueTimeOutURL, String initiator, String remarks, int amount, String securityCredential, String occassion, String commandID, String transactionID, String receiverParty, String resultURL) {
		this.recieverIdentifierType = recieverIdentifierType;
		this.queueTimeOutURL = queueTimeOutURL;
		this.initiator = initiator;
		this.remarks = remarks;
		this.amount = amount;
		this.securityCredential = securityCredential;
		this.occassion = occassion;
		this.commandID = commandID;
		this.transactionID = transactionID;
		this.receiverParty = receiverParty;
		this.resultURL = resultURL;
	}

	public String getRecieverIdentifierType() {
		return recieverIdentifierType;
	}

	public void setRecieverIdentifierType(String recieverIdentifierType) {
		this.recieverIdentifierType = recieverIdentifierType;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSecurityCredential() {
		return securityCredential;
	}

	public void setSecurityCredential(String securityCredential) {
		this.securityCredential = securityCredential;
	}

	public String getOccassion() {
		return occassion;
	}

	public void setOccassion(String occassion) {
		this.occassion = occassion;
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

	public String getReceiverParty() {
		return receiverParty;
	}

	public void setReceiverParty(String receiverParty) {
		this.receiverParty = receiverParty;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
}