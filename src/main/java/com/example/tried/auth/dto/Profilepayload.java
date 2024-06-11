package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Profilepayload{

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("fromWithin")
	private Boolean fromWithin;

	public Profilepayload() {
	}

	public Profilepayload(String mobileNumber, Boolean fromWithin) {
		this.mobileNumber = mobileNumber;
		this.fromWithin = fromWithin;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Boolean getFromWithin() {
		return fromWithin;
	}

	public void setFromWithin(Boolean fromWithin) {
		this.fromWithin = fromWithin;
	}
}