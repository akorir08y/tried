package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestMemberDetails{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Mempayload mempayload;

	public RequestMemberDetails() {
	}

	public RequestMemberDetails(String function, Mempayload mempayload) {
		this.function = function;
		this.mempayload = mempayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Mempayload getMempayload() {
		return mempayload;
	}

	public void setMempayload(Mempayload mempayload) {
		this.mempayload = mempayload;
	}
}