package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberTransferResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;

	public MemberTransferResponse() {
	}

	public MemberTransferResponse(int sessionNumber, String function, String state, int status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.state = state;
		this.status = status;
	}

	public int getSessionNumber(){
		return sessionNumber;
	}

	public String getFunction(){
		return function;
	}

	public String getState(){
		return state;
	}

	public int getStatus(){
		return status;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}