package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CheckAccountBalanceRequest{

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("PartyA")
	private int partyA;

	@JsonProperty("IdentifierType")
	private int identifierType;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("ResultURL")
	private String resultURL;

	public CheckAccountBalanceRequest() {

	}

	public CheckAccountBalanceRequest(String resultURL, String commandID, int identifierType, int partyA, String securityCredential, String remarks, String initiator, String queueTimeOutURL) {
		this.resultURL = resultURL;
		this.commandID = commandID;
		this.identifierType = identifierType;
		this.partyA = partyA;
		this.securityCredential = securityCredential;
		this.remarks = remarks;
		this.initiator = initiator;
		this.queueTimeOutURL = queueTimeOutURL;
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

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
}