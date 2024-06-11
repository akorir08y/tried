package com.example.tried.auth.reports.payment_mode;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TrustFundsSummaryWithPaymentModeResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private RPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public TrustFundsSummaryWithPaymentModeResponse() {

	}

	public TrustFundsSummaryWithPaymentModeResponse(Integer sessionNumber, RPayload payload, String function, Integer status) {
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

	public RPayload getPayload() {
		return payload;
	}

	public void setPayload(RPayload payload) {
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