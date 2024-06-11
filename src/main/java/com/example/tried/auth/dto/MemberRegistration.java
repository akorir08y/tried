package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MemberRegistration{

	@JsonProperty("payload")
	private MemberRegister payload;

	@JsonProperty("function")
	private String function;

	public MemberRegistration() {
	}

	public MemberRegistration(MemberRegister payload, String function) {
		this.payload = payload;
		this.function = function;
	}


	public MemberRegister getPayload() {
		return payload;
	}

	public void setPayload(MemberRegister payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}