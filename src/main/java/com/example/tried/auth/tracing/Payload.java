package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("startDate")
	private String startDate;

	public Payload() {

	}


	public Payload(String phoneNumber, String endDate, String startDate) {
		this.phoneNumber = phoneNumber;
		this.endDate = endDate;
		this.startDate = startDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
}