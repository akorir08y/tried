package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthMemberRegistrationResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private int status;

	@JsonProperty("state")
	private String state;

	@JsonProperty("notice")
	private String notice;

	@JsonProperty("error")
	private String error;

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("numberOfTries")
	private String numberOfTries;

	public AuthMemberRegistrationResponse() {
	}

	public AuthMemberRegistrationResponse(int sessionNumber, String function, int status, String state, String notice, String error, String membershipNumber, String numberOfTries) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.status = status;
		this.state = state;
		this.notice = notice;
		this.error = error;
		this.membershipNumber = membershipNumber;
		this.numberOfTries = numberOfTries;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public String getNumberOfTries() {
		return numberOfTries;
	}

	public void setNumberOfTries(String numberOfTries) {
		this.numberOfTries = numberOfTries;
	}
}