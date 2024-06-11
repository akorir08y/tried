package com.example.tried.auth.personnel.reports.offering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



public class LocalChurchOfferingSummaryResponse{

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public LocalChurchOfferingSummaryResponse() {
	}

	public LocalChurchOfferingSummaryResponse(String totalAmount, Integer sessionNumber, Payload payload, String function, Integer status) {
		this.totalAmount = totalAmount;
		this.sessionNumber = sessionNumber;
		this.payload = payload;
		this.function = function;
		this.status = status;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
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