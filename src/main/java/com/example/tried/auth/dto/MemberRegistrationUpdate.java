package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MemberRegistrationUpdate{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private AuthMemberRegister updatepayload;


	public MemberRegistrationUpdate() {
	}

	public MemberRegistrationUpdate(String function, AuthMemberRegister updatepayload) {
		this.function = function;
		this.updatepayload = updatepayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public AuthMemberRegister getUpdatepayload() {
		return updatepayload;
	}

	public void setUpdatepayload(AuthMemberRegister updatepayload) {
		this.updatepayload = updatepayload;
	}
}