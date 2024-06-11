package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MobileReceiveFundsResponse{

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("extraData")
	private ExtraData extraData;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("status")
	private Integer status;

	public MobileReceiveFundsResponse() {
	}

	public MobileReceiveFundsResponse(String cfmsTransactionId, Integer sessionNumber, ExtraData extraData, String function, String state, String accountNumber, Integer status) {
		this.cfmsTransactionId = cfmsTransactionId;
		this.sessionNumber = sessionNumber;
		this.extraData = extraData;
		this.function = function;
		this.state = state;
		this.accountNumber = accountNumber;
		this.status = status;
	}

	public String getCfmsTransactionId() {
		return cfmsTransactionId;
	}

	public void setCfmsTransactionId(String cfmsTransactionId) {
		this.cfmsTransactionId = cfmsTransactionId;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public ExtraData getExtraData() {
		return extraData;
	}

	public void setExtraData(ExtraData extraData) {
		this.extraData = extraData;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}