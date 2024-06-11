package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SpecificOfferingStatementResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Specpayload specpayload;

	@JsonProperty("status")
	private Integer status;

	public SpecificOfferingStatementResponse() {
	}

	public SpecificOfferingStatementResponse(String sessionNumber, String function, Specpayload specpayload, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.specpayload = specpayload;
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

	public Specpayload getSpecpayload() {
		return specpayload;
	}

	public void setSpecpayload(Specpayload specpayload) {
		this.specpayload = specpayload;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}