package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestChurchDetails{

	@JsonProperty("accessNumber")
	private String accessNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("mobileServiceProvider")
	private String mobileServiceProvider;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("connectionPurpose")
	private String connectionPurpose;

	@JsonProperty("accessPoint")
	private String accessPoint;

	public RequestChurchDetails() {
	}

	public RequestChurchDetails(String accessNumber, String function, String mobileServiceProvider, String churchCode, String connectionPurpose, String accessPoint) {
		this.accessNumber = accessNumber;
		this.function = function;
		this.mobileServiceProvider = mobileServiceProvider;
		this.churchCode = churchCode;
		this.connectionPurpose = connectionPurpose;
		this.accessPoint = accessPoint;
	}

	public String getAccessNumber() {
		return accessNumber;
	}

	public void setAccessNumber(String accessNumber) {
		this.accessNumber = accessNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
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

	public String getConnectionPurpose() {
		return connectionPurpose;
	}

	public void setConnectionPurpose(String connectionPurpose) {
		this.connectionPurpose = connectionPurpose;
	}

	public String getAccessPoint() {
		return accessPoint;
	}

	public void setAccessPoint(String accessPoint) {
		this.accessPoint = accessPoint;
	}
}