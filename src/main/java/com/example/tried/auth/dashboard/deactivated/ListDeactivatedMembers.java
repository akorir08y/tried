package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ListDeactivatedMembers{

	@JsonProperty("payload")
	private Deapayload deapayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public ListDeactivatedMembers(Deapayload deapayload, String function, Authentication authentication) {
		this.deapayload = deapayload;
		this.function = function;
		this.authentication = authentication;
	}

	public Deapayload getDeapayload() {
		return deapayload;
	}

	public void setDeapayload(Deapayload deapayload) {
		this.deapayload = deapayload;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public ListDeactivatedMembers() {
	}
}