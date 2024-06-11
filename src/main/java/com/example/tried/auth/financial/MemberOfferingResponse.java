package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberOfferingResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Offpayload offpayload;

	@JsonProperty("status")
	private Integer status;

	public MemberOfferingResponse() {
	}

	public MemberOfferingResponse(String sessionNumber, String function, Offpayload offpayload, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.offpayload = offpayload;
		this.status = status;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Offpayload getOffpayload() {
		return offpayload;
	}

	public void setOffpayload(Offpayload offpayload) {
		this.offpayload = offpayload;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}