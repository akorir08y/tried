package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MobilePayForOthersResponse{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Mobpayload mobpayload;

	public MobilePayForOthersResponse() {
	}

	public MobilePayForOthersResponse(String function, Mobpayload mobpayload) {
		this.function = function;
		this.mobpayload = mobpayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Mobpayload getMobpayload() {
		return mobpayload;
	}

	public void setMobpayload(Mobpayload mobpayload) {
		this.mobpayload = mobpayload;
	}
}