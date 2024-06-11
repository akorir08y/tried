package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TrustFundSummaryResponse{

	@JsonProperty("trpayload")
	private Trpayload trpayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public TrustFundSummaryResponse() {

	}

	public TrustFundSummaryResponse(Trpayload trpayload, String function, Integer status) {
		this.trpayload = trpayload;
		this.function = function;
		this.status = status;
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