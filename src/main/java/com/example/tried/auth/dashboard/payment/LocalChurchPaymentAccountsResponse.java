package com.example.tried.auth.dashboard.payment;

import com.fasterxml.jackson.annotation.JsonProperty;



public class LocalChurchPaymentAccountsResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Pypayload pypayload;

	public LocalChurchPaymentAccountsResponse() {
	}

	public LocalChurchPaymentAccountsResponse(Integer sessionNumber, String function, Pypayload pypayload) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.pypayload = pypayload;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Pypayload getPypayload() {
		return pypayload;
	}

	public void setPypayload(Pypayload pypayload) {
		this.pypayload = pypayload;
	}
}