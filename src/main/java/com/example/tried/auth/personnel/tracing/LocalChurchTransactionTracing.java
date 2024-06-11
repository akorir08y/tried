package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchTransactionTracing{

	@JsonProperty("payload")
	private TracingPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public LocalChurchTransactionTracing() {

	}

	public LocalChurchTransactionTracing(TracingPayload payload, String function, Authentication authentication) {
		this.payload = payload;
		this.function = function;
		this.authentication = authentication;
	}

	public TracingPayload getPayload() {
		return payload;
	}

	public void setPayload(TracingPayload payload) {
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