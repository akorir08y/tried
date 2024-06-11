package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;



public class TrustFundTranscriptResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("payload")
	private Trpayload trpayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public TrustFundTranscriptResponse() {
	}

	public TrustFundTranscriptResponse(String sessionNumber, Trpayload trpayload, String function, Integer status) {
		this.sessionNumber = sessionNumber;
		this.trpayload = trpayload;
		this.function = function;
		this.status = status;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Trpayload getTrpayload() {
		return trpayload;
	}

	public void setTrpayload(Trpayload trpayload) {
		this.trpayload = trpayload;
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