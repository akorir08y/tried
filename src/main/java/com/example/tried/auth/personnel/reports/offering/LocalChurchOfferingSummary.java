package com.example.tried.auth.personnel.reports.offering;

import com.fasterxml.jackson.annotation.JsonProperty;



public class LocalChurchOfferingSummary{

	@JsonProperty("payload")
	private LocalChurchPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public LocalChurchOfferingSummary() {
	}

	public LocalChurchOfferingSummary(LocalChurchPayload payload, String function, Authentication authentication) {
		this.payload = payload;
		this.function = function;
		this.authentication = authentication;
	}

	public LocalChurchPayload getPayload() {
		return payload;
	}

	public void setPayload(LocalChurchPayload payload) {
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