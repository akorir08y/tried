package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalTrustFundPayment{

	@JsonProperty("payload")
	private Papayload papayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public LocalTrustFundPayment() {

	}

	public LocalTrustFundPayment(Papayload papayload, String function, Authentication authentication) {
		this.papayload = papayload;
		this.function = function;
		this.authentication = authentication;
	}

	public Papayload getPapayload() {
		return papayload;
	}

	public void setPapayload(Papayload papayload) {
		this.papayload = papayload;
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