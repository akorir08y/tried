package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("previousAccessNumber")
	private String previousAccessNumber;

	@JsonProperty("currentAccessNumber")
	private String currentAccessNumber;

	public Payload() {
	}

	public Payload(String sessionNumber, String pin, String previousAccessNumber, String currentAccessNumber) {
		this.sessionNumber = sessionNumber;
		this.pin = pin;
		this.previousAccessNumber = previousAccessNumber;
		this.currentAccessNumber = currentAccessNumber;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPreviousAccessNumber() {
		return previousAccessNumber;
	}

	public void setPreviousAccessNumber(String previousAccessNumber) {
		this.previousAccessNumber = previousAccessNumber;
	}

	public String getCurrentAccessNumber() {
		return currentAccessNumber;
	}

	public void setCurrentAccessNumber(String currentAccessNumber) {
		this.currentAccessNumber = currentAccessNumber;
	}
}