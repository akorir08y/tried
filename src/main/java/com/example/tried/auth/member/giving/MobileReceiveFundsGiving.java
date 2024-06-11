package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MobileReceiveFundsGiving{

	@JsonProperty("payload")
	private RmPayload payload;

	@JsonProperty("function")
	private String function;

	public MobileReceiveFundsGiving() {
	}

	public MobileReceiveFundsGiving(RmPayload payload, String function) {
		this.payload = payload;
		this.function = function;
	}

	public RmPayload getPayload() {
		return payload;
	}

	public void setPayload(RmPayload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}