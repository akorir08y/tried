package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthMemberResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("numberOfTries")
	private int numberOfTries;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;

	@JsonProperty("notice")
	private String notice;

	@JsonProperty("error")
	private String error;


	public AuthMemberResponse() {
	}

	public AuthMemberResponse(int sessionNumber, String function, int numberOfTries, String state, int status, String notice, String error) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.numberOfTries = numberOfTries;
		this.state = state;
		this.status = status;
		this.notice = notice;
		this.error = error;
	}


	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public int getNumberOfTries() {
		return numberOfTries;
	}

	public void setNumberOfTries(int numberOfTries) {
		this.numberOfTries = numberOfTries;
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

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}