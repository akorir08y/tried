package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberPersonnelResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("payload")
	private PersonnelPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private int status;

	public MemberPersonnelResponse() {
	}

	public MemberPersonnelResponse(int sessionNumber, PersonnelPayload payload, String function, int status) {
		this.sessionNumber = sessionNumber;
		this.payload = payload;
		this.function = function;
		this.status = status;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public PersonnelPayload getPayload() {
		return payload;
	}

	public void setPayload(PersonnelPayload payload) {
		this.payload = payload;
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
}