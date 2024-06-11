package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MpesaSTKRequest{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("channel")
	private String channel;

	@JsonProperty("accountNumber")
	private String accountNumber;

	public MpesaSTKRequest() {
	}

	public MpesaSTKRequest(Integer amount, String cfmsTransactionId, String phoneNumber, String sessionNumber, String channel, String accountNumber) {
		this.amount = amount;
		this.cfmsTransactionId = cfmsTransactionId;
		this.phoneNumber = phoneNumber;
		this.sessionNumber = sessionNumber;
		this.channel = channel;
		this.accountNumber = accountNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCfmsTransactionId() {
		return cfmsTransactionId;
	}

	public void setCfmsTransactionId(String cfmsTransactionId) {
		this.cfmsTransactionId = cfmsTransactionId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}