package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalTrustFundPaymentResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Prpayload prpayload;

	@JsonProperty("status")
	private Integer status;

	public LocalTrustFundPaymentResponse() {

	}

	public LocalTrustFundPaymentResponse(Integer sessionNumber, String function, Prpayload prpayload, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.prpayload = prpayload;
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

	public Prpayload getPrpayload() {
		return prpayload;
	}

	public void setPrpayload(Prpayload prpayload) {
		this.prpayload = prpayload;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}