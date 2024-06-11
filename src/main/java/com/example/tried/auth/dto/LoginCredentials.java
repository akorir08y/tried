package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginCredentials{

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("accessNumber")
	private String accessNumber;

	public LoginCredentials() {
	}

	public LoginCredentials(String pin, String accessNumber) {
		this.pin = pin;
		this.accessNumber = accessNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getAccessNumber() {
		return accessNumber;
	}

	public void setAccessNumber(String accessNumber) {
		this.accessNumber = accessNumber;
	}
}