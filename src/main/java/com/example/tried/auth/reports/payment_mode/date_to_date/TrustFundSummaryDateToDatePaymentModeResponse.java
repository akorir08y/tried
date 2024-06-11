package com.example.tried.auth.reports.payment_mode.date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TrustFundSummaryDateToDatePaymentModeResponse{

	@JsonProperty("payload")
	private Mdpayload mdpayload;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public TrustFundSummaryDateToDatePaymentModeResponse() {

	}

	public TrustFundSummaryDateToDatePaymentModeResponse(Mdpayload mdpayload, Integer sessionNumber, String function, Integer status) {
		this.mdpayload = mdpayload;
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.status = status;
	}

	public Mdpayload getMdpayload() {
		return mdpayload;
	}

	public void setMdpayload(Mdpayload mdpayload) {
		this.mdpayload = mdpayload;
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