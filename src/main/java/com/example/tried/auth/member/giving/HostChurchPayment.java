package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HostChurchPayment{

	@JsonProperty("payload")
	private Gpayload gpayload;

	@JsonProperty("function")
	private String function;

	public HostChurchPayment() {
	}

	public HostChurchPayment(Gpayload gpayload, String function) {
		this.gpayload = gpayload;
		this.function = function;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Gpayload getGpayload() {
		return gpayload;
	}

	public void setGpayload(Gpayload gpayload) {
		this.gpayload = gpayload;
	}
}

