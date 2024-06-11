package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalTrustFundSummary{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Cppayload cppayload;

	@JsonProperty("authentication")
	private Authentication authentication;

	public LocalTrustFundSummary() {

	}

	public LocalTrustFundSummary(String function, Cppayload cppayload, Authentication authentication) {
		this.function = function;
		this.cppayload = cppayload;
		this.authentication = authentication;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Cppayload getCppayload() {
		return cppayload;
	}

	public void setCppayload(Cppayload cppayload) {
		this.cppayload = cppayload;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
}