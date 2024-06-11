package com.example.tried.auth.dashboard.trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchTrustFundSummaryResponse{

	@JsonProperty("payload")
	private Trupayload trupayload;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public LocalChurchTrustFundSummaryResponse() {
	}

	public LocalChurchTrustFundSummaryResponse(Integer status, String function, Integer sessionNumber, Trupayload trupayload) {
		this.status = status;
		this.function = function;
		this.sessionNumber = sessionNumber;
		this.trupayload = trupayload;
	}

	public Trupayload getTrupayload() {
		return trupayload;
	}

	public void setTrupayload(Trupayload trupayload) {
		this.trupayload = trupayload;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}