package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("numberOfTries")
	private Integer numberOfTries;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("startDate")
	private String startDate;

	public Payload() {
	}

	public Payload(String memberNumber, String endDate, String accountName, String memberName, Integer numberOfTries, String accountNumber, String startDate) {
		this.memberNumber = memberNumber;
		this.endDate = endDate;
		this.accountName = accountName;
		this.memberName = memberName;
		this.numberOfTries = numberOfTries;
		this.accountNumber = accountNumber;
		this.startDate = startDate;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getNumberOfTries() {
		return numberOfTries;
	}

	public void setNumberOfTries(Integer numberOfTries) {
		this.numberOfTries = numberOfTries;
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