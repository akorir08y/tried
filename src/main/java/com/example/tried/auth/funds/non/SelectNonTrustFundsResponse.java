package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;



public class SelectNonTrustFundsResponse{

	@JsonProperty("payload")
	private Nonpayload nonpayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public SelectNonTrustFundsResponse() {
	}

	public SelectNonTrustFundsResponse(Nonpayload nonpayload, String function, Integer status) {
		this.nonpayload = nonpayload;
		this.function = function;
		this.status = status;
	}

	public Nonpayload getNonpayload() {
		return nonpayload;
	}

	public void setNonpayload(Nonpayload nonpayload) {
		this.nonpayload = nonpayload;
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