package com.example.tried.auth.reports.trust_funds_date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TrustFundDateToDateResponse{

	@JsonProperty("payload")
	private Dtpayload dtpayload;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public TrustFundDateToDateResponse() {

	}

	public TrustFundDateToDateResponse(Dtpayload dtpayload, Integer sessionNumber, String function, Integer status) {
		this.dtpayload = dtpayload;
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.status = status;
	}


	public Dtpayload getDtpayload() {
		return dtpayload;
	}

	public void setDtpayload(Dtpayload dtpayload) {
		this.dtpayload = dtpayload;
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