package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Churchpayload{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("accessNumber")
	private String accessNumber;

	@JsonProperty("mobileServiceProvider")
	private String mobileServiceProvider;

	@JsonProperty("churchCode")
	private String churchCode;

	public Churchpayload() {
	}

	public Churchpayload(Integer sessionNumber, String accessNumber, String mobileServiceProvider, String churchCode) {
		this.sessionNumber = sessionNumber;
		this.accessNumber = accessNumber;
		this.mobileServiceProvider = mobileServiceProvider;
		this.churchCode = churchCode;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getAccessNumber() {
		return accessNumber;
	}

	public void setAccessNumber(String accessNumber) {
		this.accessNumber = accessNumber;
	}

	public String getMobileServiceProvider() {
		return mobileServiceProvider;
	}

	public void setMobileServiceProvider(String mobileServiceProvider) {
		this.mobileServiceProvider = mobileServiceProvider;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}
}