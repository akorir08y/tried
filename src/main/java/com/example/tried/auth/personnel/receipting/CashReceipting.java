package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CashReceipting{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	public CashReceipting() {
	}

	public CashReceipting(Payload payload, String function) {
		this.payload = payload;
		this.function = function;
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