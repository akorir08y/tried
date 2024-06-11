package com.example.tried.dto.kra;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TaxRemittanceRequest{

	@JsonProperty("SenderIdentifierType")
	private String senderIdentifierType;

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("RecieverIdentifierType")
	private String recieverIdentifierType;

	@JsonProperty("PartyA")
	private String partyA;

	@JsonProperty("PartyB")
	private String partyB;

	@JsonProperty("AccountReference")
	private String accountReference;

	@JsonProperty("Command ID")
	private String commandID;

	@JsonProperty("ResultURL")
	private String resultURL;

	public TaxRemittanceRequest() {

	}

	public TaxRemittanceRequest(String senderIdentifierType, String queueTimeOutURL, String initiator, String remarks, String amount, String securityCredential, String recieverIdentifierType, String partyA, String partyB, String accountReference, String commandID, String resultURL) {
		this.senderIdentifierType = senderIdentifierType;
		this.queueTimeOutURL = queueTimeOutURL;
		this.initiator = initiator;
		this.remarks = remarks;
		this.amount = amount;
		this.securityCredential = securityCredential;
		this.recieverIdentifierType = recieverIdentifierType;
		this.partyA = partyA;
		this.partyB = partyB;
		this.accountReference = accountReference;
		this.commandID = commandID;
		this.resultURL = resultURL;
	}

	public String getSenderIdentifierType() {
		return senderIdentifierType;
	}

	public void setSenderIdentifierType(String senderIdentifierType) {
		this.senderIdentifierType = senderIdentifierType;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSecurityCredential() {
		return securityCredential;
	}

	public void setSecurityCredential(String securityCredential) {
		this.securityCredential = securityCredential;
	}

	public String getRecieverIdentifierType() {
		return recieverIdentifierType;
	}

	public void setRecieverIdentifierType(String recieverIdentifierType) {
		this.recieverIdentifierType = recieverIdentifierType;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getAccountReference() {
		return accountReference;
	}

	public void setAccountReference(String accountReference) {
		this.accountReference = accountReference;
	}

	public String getCommandID() {
		return commandID;
	}

	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
}