package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberOffering{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private OfferingPayload payload;

	@JsonProperty("authentication")
	private OfferingAuthentication authentication;

	public MemberOffering() {
	}

	public MemberOffering(String function, OfferingPayload payload, OfferingAuthentication authentication) {
		this.function = function;
		this.payload = payload;
		this.authentication = authentication;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public OfferingPayload getPayload() {
		return payload;
	}

	public void setPayload(OfferingPayload payload) {
		this.payload = payload;
	}

	public OfferingAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(OfferingAuthentication authentication) {
		this.authentication = authentication;
	}
}