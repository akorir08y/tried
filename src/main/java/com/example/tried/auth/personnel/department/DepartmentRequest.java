package com.example.tried.auth.personnel.department;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DepartmentRequest{

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public DepartmentRequest() {
	}

	public DepartmentRequest(String function, Authentication authentication) {
		this.function = function;
		this.authentication = authentication;
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