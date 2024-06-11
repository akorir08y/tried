package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MpesaSTKRequestResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private Integer status;

	public MpesaSTKRequestResponse() {
	}

	public MpesaSTKRequestResponse(Integer sessionNumber, String function, String message, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}