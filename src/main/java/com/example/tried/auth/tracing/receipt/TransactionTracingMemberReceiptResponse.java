package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionTracingMemberReceiptResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Rpayload rpayload;

	@JsonProperty("status")
	private Integer status;

	public TransactionTracingMemberReceiptResponse() {

	}

	public TransactionTracingMemberReceiptResponse(String sessionNumber, String function, Rpayload rpayload, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.rpayload = rpayload;
		this.status = status;
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

	public Rpayload getRpayload() {
		return rpayload;
	}

	public void setRpayload(Rpayload rpayload) {
		this.rpayload = rpayload;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}