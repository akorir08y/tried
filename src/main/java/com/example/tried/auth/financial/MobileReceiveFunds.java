package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileReceiveFunds{

	@JsonProperty("receivepayload")
	private Receivepayload receivepayload;

	@JsonProperty("function")
	private String function;

	public MobileReceiveFunds() {

	}

	public MobileReceiveFunds(Receivepayload receivepayload, String function) {
		this.receivepayload = receivepayload;
		this.function = function;
	}

	public Receivepayload getReceivepayload() {
		return receivepayload;
	}

	public void setReceivepayload(Receivepayload receivepayload) {
		this.receivepayload = receivepayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}