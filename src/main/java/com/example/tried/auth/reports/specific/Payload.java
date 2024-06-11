package com.example.tried.auth.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("startDate")
	private String startDate;

	public Payload() {

	}

	public Payload(String endDate, String accountName, String startDate) {
		this.endDate = endDate;
		this.accountName = accountName;
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}