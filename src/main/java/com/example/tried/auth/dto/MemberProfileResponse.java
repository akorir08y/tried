package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberProfileResponse{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Memberpayload payload;

	public MemberProfileResponse() {
	}

	public MemberProfileResponse(String function, Memberpayload payload) {
		this.function = function;
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Memberpayload getPayload() {
		return payload;
	}

	public void setPayload(Memberpayload payload) {
		this.payload = payload;
	}
}