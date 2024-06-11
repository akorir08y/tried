package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthMemberRequest {

	@JsonProperty("payload")
	private AuthPayload payload;

	@JsonProperty("function")
	private String function;

	public AuthMemberRequest() {
	}

	public AuthMemberRequest(AuthPayload payload, String function) {
		this.payload = payload;
		this.function = function;
	}

	public AuthPayload getPayload() {
		return payload;
	}

	public void setPayload(AuthPayload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}