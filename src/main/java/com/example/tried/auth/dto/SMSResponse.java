package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SMSResponse{

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;

	public SMSResponse() {
	}

	public SMSResponse(String function, String state, int status) {
		this.function = function;
		this.state = state;
		this.status = status;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}