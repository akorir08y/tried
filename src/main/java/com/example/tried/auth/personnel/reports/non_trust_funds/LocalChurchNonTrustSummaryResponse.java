package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchNonTrustSummaryResponse{

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private NonTrpayload nonTrpayload;

	@JsonProperty("status")
	private Integer status;

	public LocalChurchNonTrustSummaryResponse() {
	}

	public LocalChurchNonTrustSummaryResponse(String totalAmount, Integer sessionNumber, String function, NonTrpayload nonTrpayload, Integer status) {
		this.totalAmount = totalAmount;
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.nonTrpayload = nonTrpayload;
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public NonTrpayload getNonTrpayload() {
		return nonTrpayload;
	}

	public void setNonTrpayload(NonTrpayload nonTrpayload) {
		this.nonTrpayload = nonTrpayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}