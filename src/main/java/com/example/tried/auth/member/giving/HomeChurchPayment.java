package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HomeChurchPayment{

	@JsonProperty("payload")
	private HPayload payload;

	@JsonProperty("function")
	private String function;

	public HomeChurchPayment() {
	}

	public HomeChurchPayment(HPayload payload, String function) {
		this.payload = payload;
		this.function = function;
	}

	public HPayload getPayload() {
		return payload;
	}

	public void setPayload(HPayload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}