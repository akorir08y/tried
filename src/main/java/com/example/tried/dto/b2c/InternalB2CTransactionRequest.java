package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;


public class InternalB2CTransactionRequest{

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("InitiatorName")
	private String initiatorName;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("Occassion")
	private String occassion;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("PartyA")
	private int partyA;

	@JsonProperty("PartyB")
	private long partyB;

	@JsonProperty("ResultURL")
	private String resultURL;

	public InternalB2CTransactionRequest() {

	}

	public InternalB2CTransactionRequest(String queueTimeOutURL, String remarks, int amount, String initiatorName, String securityCredential, String occassion, String commandID, int partyA, long partyB, String resultURL) {
		this.queueTimeOutURL = queueTimeOutURL;
		this.remarks = remarks;
		this.amount = amount;
		this.initiatorName = initiatorName;
		this.securityCredential = securityCredential;
		this.occassion = occassion;
		this.commandID = commandID;
		this.partyA = partyA;
		this.partyB = partyB;
		this.resultURL = resultURL;
	}

	public String getQueueTimeOutURL() {
		return queueTimeOutURL;
	}

	public void setQueueTimeOutURL(String queueTimeOutURL) {
		this.queueTimeOutURL = queueTimeOutURL;
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

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
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

	public int getPartyA() {
		return partyA;
	}

	public void setPartyA(int partyA) {
		this.partyA = partyA;
	}

	public long getPartyB() {
		return partyB;
	}

	public void setPartyB(long partyB) {
		this.partyB = partyB;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
}