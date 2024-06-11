package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MobileReceiveFundsTransferResponse{

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private Integer status;

	public MobileReceiveFundsTransferResponse() {

	}

	public MobileReceiveFundsTransferResponse(String cfmsTransactionId, String sessionNumber, String function, String state, Integer status) {
		this.cfmsTransactionId = cfmsTransactionId;
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.state = state;
		this.status = status;
	}

	public String getCfmsTransactionId() {
		return cfmsTransactionId;
	}

	public void setCfmsTransactionId(String cfmsTransactionId) {
		this.cfmsTransactionId = cfmsTransactionId;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}