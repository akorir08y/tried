package com.example.tried.auth.personnel.accounts.new_account;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateLocalChurchAccountResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private Integer status;

	public CreateLocalChurchAccountResponse() {
	}

	public CreateLocalChurchAccountResponse(Integer sessionNumber, String function, String state, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.state = state;
		this.status = status;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}