package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("transactionId")
	private String transactionId;

	public Payload() {

	}

	public Payload(String endDate, String startDate, String transactionId) {
		this.endDate = endDate;
		this.startDate = startDate;
		this.transactionId = transactionId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}