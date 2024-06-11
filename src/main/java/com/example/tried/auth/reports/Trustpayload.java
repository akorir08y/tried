package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Trustpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("startDate")
	private String startDate;


	public Trustpayload() {

	}

	public Trustpayload(String churchName, String endDate, String accountName, String churchCode, String accountNumber, String startDate) {
		this.churchName = churchName;
		this.endDate = endDate;
		this.accountName = accountName;
		this.churchCode = churchCode;
		this.accountNumber = accountNumber;
		this.startDate = startDate;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
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

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}