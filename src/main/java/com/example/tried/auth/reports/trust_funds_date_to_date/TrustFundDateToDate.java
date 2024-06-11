package com.example.tried.auth.reports.trust_funds_date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TrustFundDateToDate{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public TrustFundDateToDate() {

	}

	public TrustFundDateToDate(Payload payload, String function, Authentication authentication) {
		this.payload = payload;
		this.function = function;
		this.authentication = authentication;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
}