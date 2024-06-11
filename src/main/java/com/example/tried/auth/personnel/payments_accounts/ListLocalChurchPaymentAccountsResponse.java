package com.example.tried.auth.personnel.payments_accounts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ListLocalChurchPaymentAccountsResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	public ListLocalChurchPaymentAccountsResponse() {
	}

	public ListLocalChurchPaymentAccountsResponse(Integer sessionNumber, Payload payload, String function) {
		this.sessionNumber = sessionNumber;
		this.payload = payload;
		this.function = function;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}