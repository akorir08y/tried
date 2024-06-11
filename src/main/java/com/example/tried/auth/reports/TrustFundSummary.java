package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;



public class TrustFundSummary{

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	@JsonProperty("trustpayload")
	private Trustpayload trustpayload;

	public TrustFundSummary() {

	}

	public TrustFundSummary(String function, Authentication authentication, Trustpayload trustpayload) {
		this.function = function;
		this.authentication = authentication;
		this.trustpayload = trustpayload;
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

	public Trustpayload getTrustpayload() {
		return trustpayload;
	}

	public void setTrustpayload(Trustpayload trustpayload) {
		this.trustpayload = trustpayload;
	}
}