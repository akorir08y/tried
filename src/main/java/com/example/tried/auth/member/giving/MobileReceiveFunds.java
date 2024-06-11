package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



public class MobileReceiveFunds{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Rpayload rpayload;

	public MobileReceiveFunds() {
	}

	public MobileReceiveFunds(String function, Rpayload rpayload) {
		this.function = function;
		this.rpayload = rpayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Rpayload getRpayload() {
		return rpayload;
	}

	public void setRpayload(Rpayload rpayload) {
		this.rpayload = rpayload;
	}
}