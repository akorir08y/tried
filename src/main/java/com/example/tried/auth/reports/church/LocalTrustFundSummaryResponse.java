package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalTrustFundSummaryResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public LocalTrustFundSummaryResponse() {

	}

	public LocalTrustFundSummaryResponse(Integer sessionNumber, Payload payload, String function, Integer status) {
		this.sessionNumber = sessionNumber;
		this.payload = payload;
		this.function = function;
		this.status = status;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}