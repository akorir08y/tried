package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthMemberReset{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	public AuthMemberReset() {
	}

	public AuthMemberReset(Payload payload, String function) {
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