package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberPersonnelReset{

	@JsonProperty("payload")
	private ResetPayload payload;

	@JsonProperty("function")
	private String function;

	public MemberPersonnelReset() {
	}

	public MemberPersonnelReset(ResetPayload payload, String function) {
		this.payload = payload;
		this.function = function;
	}

	public ResetPayload getPayload() {
		return payload;
	}

	public void setPayload(ResetPayload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}